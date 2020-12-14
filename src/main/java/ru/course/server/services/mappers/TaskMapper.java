package ru.course.server.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.course.server.persistence.dao.UserRepository;
import ru.course.server.persistence.domain.InputDataDto;
import ru.course.server.persistence.domain.OutputDataDto;
import ru.course.server.persistence.domain.Task;
import ru.course.server.persistence.domain.VariablesDto;
import ru.course.server.persistence.dto.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    @Autowired
    private UserRepository userRepository;

    public Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();

        task.setName(taskDto.getName());
        task.setConditions(taskDto.getConditions());
        task.setCreator(userRepository.findByEmail(taskDto.getCreator()));
        task.setMainFuncName(taskDto.getMainFuncName());
        task.setMainFuncType(taskDto.getMainFuncType());


        for (InputDataDto input : taskDto.getInput()) {
            task.getInput().add(input);
        }

        for (OutputDataDto output : taskDto.getOutput()) {
            task.getOutput().add(output);
        }


        for (VariablesDto variable : taskDto.getVariables()) {
          task.getVariables().add(variable);
        }

        return task;
    }


}
