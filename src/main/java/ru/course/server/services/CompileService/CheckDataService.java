package ru.course.server.services.CompileService;

import org.springframework.stereotype.Service;
import ru.course.server.compiler.Compiler;
import ru.course.server.persistence.domain.Input;
import ru.course.server.persistence.domain.Output;
import ru.course.server.persistence.domain.Variable;

import java.io.IOException;
import java.util.*;

@Service
public class CheckDataService {

public String checkData(Set<Input> input, Set<Output> output, Set<Variable> variables , String classToCompile) throws IOException {

    //Создаём компилятор
    Compiler compiler = new Compiler();

       Iterator<Input> inputDataDtoIterator =input.iterator();
       Iterator<Output> outputDataDtoIterator = output.iterator();

       List<Input> sortedInputList = new ArrayList<>(input);
       sortedInputList.sort(Comparator.comparing(e->e.getId()));

       List<Output> sortedOutputList = new ArrayList<>(output);
       sortedOutputList.sort(Comparator.comparing(e->e.getId()));

       List<Variable> sortedVariablesList = new ArrayList<>(variables);
       sortedVariablesList.sort(Comparator.comparing(e->e.getId()));

       Iterator<Input> inputIterator = sortedInputList.iterator();
       Iterator<Output> outputIterator = sortedOutputList.iterator();

       while(inputIterator.hasNext() && outputIterator.hasNext()){
           String inputData ="";
           for(Variable variable : sortedVariablesList){
               inputData+=inputIterator.next().getValue()+" ";
           }

           System.out.println(inputData);
           String outputData = outputIterator.next().getValue();
           String compileResult = compiler.compile(classToCompile,inputData.trim());
           System.out.println(compareResults(outputData.trim(),compileResult.trim()));

           System.out.println("compile result:" + compileResult);
           System.out.println("outputData:" +outputData);



       }

return "ы";
}



    public boolean compareResults(String expected,String res){
        return expected.equals(res);
    }


}
