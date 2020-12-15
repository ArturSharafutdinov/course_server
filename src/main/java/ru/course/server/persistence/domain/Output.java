package ru.course.server.persistence.domain;

import javax.persistence.*;

@Entity
@Table(name="output")
public class Output extends LongIdEntity{

    @Column(name="type",nullable = false)
    String type;

    @Column(name="value",nullable = false)
    String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Output() {

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Output(String type, String value) {
        this.type = type;
        this.value = value;
    }

}
