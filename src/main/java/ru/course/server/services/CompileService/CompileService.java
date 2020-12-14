package ru.course.server.services.CompileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.course.server.persistence.dao.TaskRepository;
import ru.course.server.persistence.domain.InputDataDto;
import ru.course.server.persistence.domain.Task;
import ru.course.server.compiler.Compiler;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CompileService {

    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    private EntityManager entityManager;

//TODO: добавить шаблоны для всех типов и видов возвращаемых функций

    public String compile(String taskName, String code) throws IOException {
        Task task = taskRepository.findByName(taskName);
        String onlyOneSpace = code.trim().replaceAll(" +", " ");
        if(!onlyOneSpace.contains("public "+task.getMainFuncType()+" "+task.getMainFuncName())){
            System.out.println("Переопределена главная функция");
        }

        String template = "";
        int varCount = task.getInput().size();
        String params ="";
        String converters="";
        int i=0;
        for(InputDataDto inputDataDto : task.getInput()){
            if(inputDataDto.getType().equals("int")){
                params+="Integer.parseInt("+"args["+i+"]"+"),";
            }
            else {
                params += "args[" + i + "],";
            }
            i++;
        }
        template= new String(Files.readAllBytes(Paths.get("C:\\Users\\Артур\\Desktop\\Java\\Projects\\Course\\course_server\\src\\main\\java\\ru\\course\\server\\utils\\Template1")));
       String finalClass =   template
               .replace("mainFuncPlace",code)
               .replace("mainFuncCall",task.getMainFuncName()+"("+params.
                       substring(0,params.length()-1)+")")
               .replace("converters",converters);

        System.out.println(finalClass);
       Compiler compiler = new Compiler();
       String testData="";
       for(InputDataDto inputDataDto : task.getInput()){
           testData+=inputDataDto.getValue()+" ";
       }
        System.out.println( compiler.compile(finalClass,testData.trim()));



        return "test";
    }


}
