package ru.course.server.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.course.server.persistence.dao.UserRepository;
import ru.course.server.persistence.domain.Input;
import ru.course.server.persistence.domain.Output;
import ru.course.server.persistence.domain.Variable;
import ru.course.server.persistence.dto.InputDataDto;
import ru.course.server.persistence.dto.OutputDataDto;
import ru.course.server.persistence.domain.Task;
import ru.course.server.persistence.dto.VariablesDto;
import ru.course.server.persistence.dto.*;

import java.util.LinkedHashSet;
import java.util.Set;

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


        Set<Input> inputSet = new LinkedHashSet<>();
        for (InputDataDto input : taskDto.getInput()) {
            Input input1 = new Input(input.getType(),input.getValue());
           inputSet.add(input1);
           input1.setTask(task);
        }
        task.setInput(inputSet);


        Set<Output> outputSet = new LinkedHashSet<>();
        for (OutputDataDto output : taskDto.getOutput()) {
            Output output1 = new Output(output.getType(),output.getValue());
            outputSet.add(output1);
            output1.setTask(task);
        }
        task.setOutput(outputSet);

        Set<Variable> variableSet = new LinkedHashSet<>();
        for(VariablesDto variable : taskDto.getVariables()){
            Variable variable1 = new Variable(variable.getType(), variable.getName());
            variableSet.add(variable1);
            variable1.setTask(task);
        }
        task.setVariables(variableSet);



        return task;
    }


}
