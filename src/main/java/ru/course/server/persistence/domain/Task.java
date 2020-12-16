package ru.course.server.persistence.domain;



import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="tasks")
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

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "task")
    private Set<Input> input;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "task")
    private Set<Output> output;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "task")
    private Set<Variable> variables;


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

    public Set<Input> getInput() {
        return input;
    }

    public void setInput(Set<Input> input) {
        this.input = input;
    }

    public Set<Output> getOutput() {
        return output;
    }

    public void setOutput(Set<Output> output) {
        this.output = output;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public void setVariables(Set<Variable> variables) {
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
