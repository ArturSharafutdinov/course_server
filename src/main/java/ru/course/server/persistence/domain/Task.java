package ru.course.server.persistence.domain;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tasks")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Task extends LongIdEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "conditions",nullable = false)
    private String conditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User creator;

    @Column(name = "mainFuncName",nullable = false)
    private String mainFuncName;

    @Column(name = "mainFuncType",nullable = false)
    private String mainFuncType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "task_input", joinColumns = @JoinColumn(name = "task_id"))
    private Set<InputDataDto> input = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "task_output", joinColumns = @JoinColumn(name = "task_id"))
    private Set<OutputDataDto> output = new HashSet<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "task_variables", joinColumns = @JoinColumn(name = "task_id"))
    private Set<VariablesDto> variables = new HashSet<>();


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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
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

    public Set<InputDataDto> getInput() {
        return input;
    }

    public void setInput(Set<InputDataDto> input) {
        this.input = input;
    }

    public Set<OutputDataDto> getOutput() {
        return output;
    }

    public void setOutput(Set<OutputDataDto> output) {
        this.output = output;
    }

    public Set<VariablesDto> getVariables() {
        return variables;
    }

    public void setVariables(Set<VariablesDto> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", conditions='" + conditions + '\'' +
                ", creator=" + creator +
                ", mainFuncName='" + mainFuncName + '\'' +
                ", mainFuncType='" + mainFuncType + '\'' +
                ", input=" + input +
                ", output=" + output +
                ", variables=" + variables +
                "} " ;
    }
}
