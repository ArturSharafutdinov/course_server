package ru.course.server.persistence.dto;

public class TaskDto {

    private String name;

    private String conditions;

    private String creator;

    private String mainFuncName;

    private String mainFuncType;

    private InputDataDto[] input;

    private OutputDataDto[] output;

    private VariablesDto[] variables;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMainFuncName() {
        return mainFuncName;
    }

    public void setMainFuncName(String mainFuncName) {
        this.mainFuncName = mainFuncName;
    }

    public String getMainFuncType() {
        return mainFuncType;
    }

    public void setMainFuncType(String mainFuncType) {
        this.mainFuncType = mainFuncType;
    }

    public InputDataDto[] getInput() {
        return input;
    }

    public void setInput(InputDataDto[] input) {
        this.input = input;
    }

    public OutputDataDto[] getOutput() {
        return output;
    }

    public void setOutput(OutputDataDto[] output) {
        this.output = output;
    }

    public VariablesDto[] getVariables() {
        return variables;
    }

    public void setVariables(VariablesDto[] variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", conditions='" + conditions + '\'' +
                ", creator='" + creator + '\'' +
                ", mainFuncName='" + mainFuncName + '\'' +
                ", mainFuncType='" + mainFuncType + '\'' +
                '}';
    }
}
