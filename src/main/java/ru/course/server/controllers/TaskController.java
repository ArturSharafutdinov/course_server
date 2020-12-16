package ru.course.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.course.server.persistence.dao.TaskRepository;
import ru.course.server.persistence.domain.Task;
import ru.course.server.persistence.dto.CodeDto;
import ru.course.server.persistence.dto.TaskDto;
import ru.course.server.services.CompileService.CompileService;
import ru.course.server.services.mappers.TaskMapper;

import java.io.IOException;
@CrossOrigin("http://localhost:8090")
@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CompileService compileService;

//    @PostMapping("/compile")
//    @ResponseBody
//    public String compile(@RequestBody Task task) throws IOException {
//        Compiler compiler = new Compiler();
//        return compiler.compile(task.getCode().replaceAll("\\\\", ""));
//    }

    @PostMapping("/createTask")
    @ResponseBody
    public String create(@RequestBody TaskDto task) throws IOException {
        Task taskFromDb = taskRepository.findByName(task.getName());
       if(taskFromDb==null) {
           taskRepository.save(taskMapper.mapToEntity(task));
           return "created";
       }
        System.out.println(taskFromDb);
       return "error, task with this name already exists";
    }

    @PostMapping("/removeTask/{id}")
    public String remove(@PathVariable Long id){
       taskRepository.deleteById(id);
       return "removed";
    }

    @PostMapping("/task/{id}")
    @ResponseBody
    public String compile(@RequestBody CodeDto code, @PathVariable Long id) throws IOException {
      return compileService.compile(taskRepository.findById(id).orElse(null).getName(),code.getCode());

    }

}

