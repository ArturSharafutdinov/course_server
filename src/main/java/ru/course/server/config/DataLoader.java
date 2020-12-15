package ru.course.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.course.server.persistence.dao.TaskRepository;
import ru.course.server.services.CompileService.CompileService;

import java.io.IOException;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CompileService compileService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        try {
//            System.out.println(compileService.compile("sum","public int sum(int firstNumber, int lastNumber){\n" +
//                    "return firstNumber+lastNumber;\n" +
//                    "}"));;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    }

