package ru.course.server.services.CompileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.course.server.persistence.dao.TaskRepository;
import ru.course.server.persistence.domain.Task;

import java.io.IOException;

@Service
public class CompileService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ClassCreatorService classCreatorService;

    @Autowired
    private CheckDataService checkDataService;



    // Возвращаем результаты тестов
    public String compile(String taskName, String code) throws IOException {
        Task task = taskRepository.findByName(taskName);

        String classToCompile = classCreatorService.classConstruction(task,code);
        System.out.println(classToCompile);



       if(classToCompile.equals("main function changed")){
           return classToCompile;
       }


       checkDataService.checkData(task.getInput(),task.getOutput(),task.getVariables(),classToCompile);




        return "test";
    }


}
