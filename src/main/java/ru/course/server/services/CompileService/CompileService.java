package ru.course.server.services.CompileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.course.server.persistence.dao.TaskRepository;
import ru.course.server.persistence.dto.InputDataDto;
import ru.course.server.persistence.dto.OutputDataDto;
import ru.course.server.persistence.domain.Task;
import ru.course.server.compiler.Compiler;
import ru.course.server.persistence.dto.VariablesDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

@Service
public class CompileService {

//    @Autowired
//    private TaskRepository taskRepository;
//
//    public String removeExtraSpaces(String stringToChange){
//        return stringToChange.trim().replaceAll(" +", " ");
//    }
//
//    public String fillParamsOfMainFunc(Set<VariablesDto> variables){
//        String params="";
//        int i=0;
//        for(VariablesDto variablesDto :  variables){
//            if(variablesDto.getType().equals("int")){
//                params+="Integer.parseInt("+"args["+i+"]"+"),";
//            }
//            else {
//                params += "args[" + i + "],";
//            }
//            i++;
//        }
//        return params.substring(0,params.length()-1); // убираем последнюю запятую
//    }
//
//    public String getTypeOfListOrArray(String mainFuncType){
//        if(mainFuncType.contains("Integer") || mainFuncType.contains("int")){
//            return "int";
//        }
//        if(mainFuncType.contains("Boolean") || mainFuncType.contains("boolean")){
//            return "boolean";
//        }
//        if(mainFuncType.contains("Short") || mainFuncType.contains("short")){
//            return "short";
//        }
//        if(mainFuncType.contains("Byte") || mainFuncType.contains("byte")){
//            return "byte";
//        }
//        if(mainFuncType.contains("Double") || mainFuncType.contains("double")){
//            return "double";
//        }
//        if(mainFuncType.contains("String") ){
//            return "String";
//        }
//        if(mainFuncType.contains("Character") || mainFuncType.contains("char")){
//            return "char";
//        }
//        if(mainFuncType.contains("Long") || mainFuncType.contains("long")){
//            return "long";
//        }
//        if(mainFuncType.contains("Float") || mainFuncType.contains("float")){
//            return "float";
//        }
//        return "";
//
//    }
//
//    public String[] mainFuncAndImportsConstruction(String mainFuncType,String mainFuncName, String params){
//
//        //First for mainFunc, second for imports
//        String result[] = new String[2];
//
//        // У каждого возвращаемого типа главной функции отдельный шаблон
//        String mainFuncCall="";
//
//        String imports="";
//
//        //Означает, что результат будет выводиться сразу в главной функции, либо мы проверяем действие функции в методе main
//        if(mainFuncType.equals("void")){
//            mainFuncCall="sampleClass."+mainFuncName+"("+params+");";
//        }
//        else
//            //Означает, что в методе main надо вывести все значения из списка или массива и проверить на соответствие с выходными данными
//            if(mainFuncType.contains("List") || mainFuncType.contains("[]")){
//                if(mainFuncType.contains("List")){
//                    imports="import java.util.ArrayList;\n" +
//                            "import java.util.List;\n" +
//                            "import java.util.LinkedList;";
//                }
//                mainFuncCall="for("+getTypeOfListOrArray(mainFuncType)+" var: "+"sampleClass."+mainFuncName+"("+params+"))" +
//                        "System.out.print(var +\" \");";
//            }
//            else{
//                mainFuncCall="System.out.println(sampleClass."+mainFuncName+"("+params+"));";
//            }
//            result[0]=mainFuncCall;
//            result[1]=imports;
//            return result;
//    }
//
//    public String classConstruction(Task task, String code) throws IOException {
//
//        //Убираем лишние пробелы из пользовательского кода, чтобы проверить наличие главной функции
//        String formattedCode = removeExtraSpaces(code);
//        //Тип главной функции
//        String mainFuncType = task.getMainFuncType();
//        //Имя главной функции
//        String mainFuncName = task.getMainFuncName();
//
//        //Проверяем изменял ли кто-то нашу главную функцию
//        if(!formattedCode.contains("public "+mainFuncType+" "+mainFuncName)){
//           return ("main function changed");
//        }
//
//        //Формирование параметров, с которыми мы будем запускать функцию
//        //Тут же мы сразу добавляем конвертеры для всех типов данных
//        String params =fillParamsOfMainFunc(task.getVariables());
//
//        //Получаем сформированную главную функцию и импорт необходимых библиотек
//        String[] mainFuncAndImports = mainFuncAndImportsConstruction(mainFuncType,mainFuncName,params);
//        String mainFuncCall=mainFuncAndImports[0];
//        String imports=mainFuncAndImports[1];
//
//
//        //Шаблон для всех классов, в который мы подставляем все строчки для полной комплияции пользовательского кода
//        String template = "";
//        template= new String(Files.readAllBytes(Paths.get("C:\\Users\\Артур\\Desktop\\Java\\Projects\\Course\\course_server\\src\\main\\java\\ru\\course\\server\\utils\\Template1")));
//       String finalClass =   template
//               .replace("mainFuncPlace",code)
//               .replace("mainFuncCall",mainFuncCall)
//               .replace("imports",imports);
//
//
//        return finalClass;
//    }
//
//    // Возвращаем результаты тестов
//    public String compile(String taskName, String code) throws IOException {
//        Task task = taskRepository.findByName(taskName);
//
//        String classToCompile = classConstruction(task,code);
//
//       if(classToCompile.equals("main function changed")){
//           return classToCompile;
//       }
//
//       int countVariables = task.getVariables().size();
//
//       //Создаём компилятор
//        Compiler compiler = new Compiler();
//
////       Iterator<InputDataDto> inputDataDtoIterator = task.getInput().iterator();
////       Iterator<OutputDataDto> outputDataDtoIterator = task.getOutput().iterator();
////       boolean checkAllData = true;
////       while(inputDataDtoIterator.hasNext() && outputDataDtoIterator.hasNext()){
////           String inputData="";
////           String outputData="";
////           for(int i=0;i<countVariables;i++){
////               inputData +=inputDataDtoIterator.next().getValue()+" ";
////          }
////           outputData +=outputDataDtoIterator.next().getValue()+" ";
////
////           System.out.println(inputData);
////           System.out.println(outputData);
////
////           String compilerResult = compiler.compile(classToCompile,inputData.trim());
////           String outputToCompare = outputData.trim();
////
////           if(!compareResults(outputToCompare.trim(),compilerResult.trim())){
////               checkAllData=false;
////           }
////
////       }
////return checkAllData?"Все тесты успешно пройдены":"Один или несколько тестов не пройдены";
//        return "test";
//    }
//
//
//
//
//    public boolean compareResults(String expected,String res){
//       return expected.equals(res);
//    }


}
