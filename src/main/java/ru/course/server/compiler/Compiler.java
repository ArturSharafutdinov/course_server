package ru.course.server.compiler;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {

    public static Pattern patternForMainClassName = Pattern.compile("public class (\\D{1}\\w+Class)");
    public static String fileDirectory = "C:\\Users\\Артур\\Desktop\\Java\\Projects\\Course\\course_server\\src\\main\\java\\ru\\course\\server\\tempFiles";

    public String compile(String code, String testData) throws IOException {
        Matcher matcher = patternForMainClassName.matcher(code);
        String filename ="";
        if(matcher.find())
            filename=matcher.group(1);
        else{
            //просто возвращаем ошибку, что не найден главный класс
        }
        //Создаём java файл нашего полученного класса
        File fn = new File(fileDirectory+"\\"+filename+".java");

        //Выведем строку, полученную от клиента в только что созданный файл
        FileOutputStream fos = new FileOutputStream(fn);
        byte[] sourcecode = code.getBytes();
        fos.write(sourcecode);
        fos.close();

        String res =run(fn.getAbsolutePath(),testData);

        fn.delete();

        return res;


    }


    public String run(String absolutePathOfClassFile, String testData) throws IOException {
        String runcmd = "java " + absolutePathOfClassFile+" "+testData;
        Process exe = Runtime.getRuntime().exec(runcmd);
        String res="";
        try{
            BufferedReader bin = new BufferedReader(new InputStreamReader(exe.getInputStream()));
            BufferedReader berr = new BufferedReader(new InputStreamReader(exe.getErrorStream()));
            while(true){
                String temp=bin.readLine();
                if(temp==null){
                    break;
                }
                else{
                    res+=temp+"\n";
                }
            }
            if(res.equals("")){
                while(true){
                    String temp = berr.readLine();
                    if(temp==null){
                        break;
                    }
                    else{
                        res+=temp+"\n";
                    }
                }
            }
            bin.close();
            berr.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return res;

    }


}
