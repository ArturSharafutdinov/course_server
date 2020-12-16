package ru.course.server.services.CompileService;

import org.springframework.stereotype.Service;
import ru.course.server.persistence.domain.Task;
import ru.course.server.persistence.domain.Variable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

@Service
public class ClassCreatorService {

    public String i="0";
    public int arrayCount=0;

    public String removeExtraSpaces(String stringToChange){
        return stringToChange.trim().replaceAll(" +", " ");
    }

    // Для создания конвертов входных аргументов String[] args
    public String[] fillParamsAndConverters(Set<Variable> variables){
        String[] result = new String[2];
        String params="";
        String converters="";
        for(Variable variable :  variables){
            if(variable.getType().equals("int")){
                params+=String.format(getParserTemplate(variable.getType()),"args["+i+"]")+",";
            }
            if(variable.getType().contains("[")){
                params+="array"+arrayCount+",";
                converters+=getConverter(variable.getType());
                arrayCount++;
            }
            i+="+1";
        }
        result[0]=params.substring(0,params.length()-1);
        result[1]=converters;
         return result; // убираем последнюю запятую
    }


    //Если массив или лист, то нужно их вывести соответственно в методе main
    public String getConverter(String variableType){
        String arrayType = getTypeOfListOrArray(variableType);
        String arraySize = "int array"+arrayCount+"size = Integer.parseInt(args["+i+"]);";
        String arrayInit = arrayType+"[] array"+arrayCount+"= new "+arrayType+"[array"+arrayCount+"size];";
        String arrayFill = "for(int j=0;j<array"+arrayCount+"size;j++){" +
                "int index="+i+"+j+1;"+
                "array"+arrayCount+"[j]=" + String.format(getParserTemplate(arrayType),"args[index]")+";"+
                "}";
        i=i+"+array"+arrayCount+".length";
        return arraySize+"\n"+arrayInit+"\n"+arrayFill+"\n";
    }

    public String getParserTemplate(String arrayType){
        if(arrayType.equals("int")){
            return "Integer.parseInt(%s)";
        }
        if(arrayType.equals("double")){
            return "Double.parseDouble(%s)";
        }
        if(arrayType.equals("char")){
            return "%s.charAt(0)";
        }
        if(arrayType.equals("byte")){
            return "Byte.parseByte(%s)";
        }
        if(arrayType.equals("short")){
            return "Short.parseShort(%s)";
        }
        if(arrayType.equals("float")){
            return "Float.parseFloat(%s)";
        }
        if(arrayType.equals("long")){
            return "Long.parseLong(%s)";
        }
        if(arrayType.equals("boolean")){
            return "Boolean.parseBoolean(%s)";
        }
        return "%s";
    }


    //Если массив или лист, то нужно их вывести соответственно в методе main
    public String getTypeOfListOrArray(String mainFuncType){
        if(mainFuncType.contains("Integer") || mainFuncType.contains("int")){
            return "int";
        }
        if(mainFuncType.contains("Boolean") || mainFuncType.contains("boolean")){
            return "boolean";
        }
        if(mainFuncType.contains("Short") || mainFuncType.contains("short")){
            return "short";
        }
        if(mainFuncType.contains("Byte") || mainFuncType.contains("byte")){
            return "byte";
        }
        if(mainFuncType.contains("Double") || mainFuncType.contains("double")){
            return "double";
        }
        if(mainFuncType.contains("String") ){
            return "String";
        }
        if(mainFuncType.contains("Character") || mainFuncType.contains("char")){
            return "char";
        }
        if(mainFuncType.contains("Long") || mainFuncType.contains("long")){
            return "long";
        }
        if(mainFuncType.contains("Float") || mainFuncType.contains("float")){
            return "float";
        }
        return "";

    }

    public String[] mainFuncAndImportsConstruction(String mainFuncType,String mainFuncName, String params){

        //First for mainFunc, second for imports
        String result[] = new String[2];

        // У каждого возвращаемого типа главной функции отдельный шаблон
        String mainFuncCall="";

        String imports="";

        //Означает, что результат будет выводиться сразу в главной функции, либо мы проверяем действие функции в методе main
        if(mainFuncType.equals("void")){
            mainFuncCall="sampleClass."+mainFuncName+"("+params+");";
        }
        else
            //Означает, что в методе main надо вывести все значения из списка или массива и проверить на соответствие с выходными данными
            if(mainFuncType.contains("List") || mainFuncType.contains("[]")){
                if(mainFuncType.contains("List")){
                    imports="import java.util.ArrayList;\n" +
                            "import java.util.List;\n" +
                            "import java.util.LinkedList;";
                }
                mainFuncCall="for("+getTypeOfListOrArray(mainFuncType)+" var: "+"sampleClass."+mainFuncName+"("+params+"))" +
                        "System.out.print(var +\" \");";
            }
            else{
                mainFuncCall="System.out.println(sampleClass."+mainFuncName+"("+params+"));";
            }
        result[0]=mainFuncCall;
        result[1]=imports;
        return result;
    }

    public String classConstruction(Task task, String code) throws IOException {

        //Убираем лишние пробелы из пользовательского кода, чтобы проверить наличие главной функции
        String formattedCode = removeExtraSpaces(code);
        //Тип главной функции
        String mainFuncType = task.getMainFuncType();
        //Имя главной функции
        String mainFuncName = task.getMainFuncName();

        //Проверяем изменял ли кто-то нашу главную функцию
        if(!formattedCode.contains("public "+mainFuncType+" "+mainFuncName)){
           return ("main function changed");
        }

        //Формирование параметров, с которыми мы будем запускать функцию
        //Тут же мы сразу добавляем конвертеры для всех типов данных
        String[] paramsAndConverters = fillParamsAndConverters(task.getVariables());
        String params =paramsAndConverters[0];
        String converters = paramsAndConverters[1];

        //Получаем сформированную главную функцию и импорт необходимых библиотек
        String[] mainFuncAndImports = mainFuncAndImportsConstruction(mainFuncType,mainFuncName,params);
        String mainFuncCall=mainFuncAndImports[0];
        String imports=mainFuncAndImports[1];


        //Шаблон для всех классов, в который мы подставляем все строчки для полной комплияции пользовательского кода
        String template = "";
        template= new String(Files.readAllBytes(Paths.get("C:\\Users\\Артур\\Desktop\\Java\\Projects\\Course\\course_server\\src\\main\\java\\ru\\course\\server\\utils\\Template1")));
       String finalClass =   template
               .replace("mainFuncPlace",code)
               .replace("mainFuncCall",mainFuncCall)
               .replace("imports",imports)
               .replace("converters",converters);


        return finalClass;
    }



}
