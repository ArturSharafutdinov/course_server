package ru.course.server.persistence.dto;

import javax.persistence.Embeddable;


public class VariablesDto {

    private String type;

    private String name;

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

    @Override
    public String toString() {
        return "VariablesDto{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
