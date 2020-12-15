package ru.course.server.persistence.domain;

import javax.persistence.*;

@Entity
@Table(name="variables")
public class Variable extends LongIdEntity{

    @Column(name="type",nullable = false)
    String type;

    @Column(name="name",nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Variable() {

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Variable(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
