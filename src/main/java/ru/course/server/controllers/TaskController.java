package ru.course.server.controllers;

import org.springframework.web.bind.annotation.*;
import ru.course.server.persistence.domain.Compiler;
import ru.course.server.persistence.domain.Task;

import java.io.IOException;
@CrossOrigin("http://localhost:8090")
@RestController
public class TaskController {

    @PostMapping("/compile")
    @ResponseBody
    public String compile(@RequestBody Task task) throws IOException {
        Compiler compiler = new Compiler();
        return compiler.compile(task.getCode().replaceAll("\\\\", ""));
    }

}

