package ru.course.server.persistence.domain;

import javax.persistence.Embeddable;

@Embeddable
public class OutputDataDto {

    private String type;

    private String value;

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

    @Override
    public String toString() {
        return "OutputDataDto{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}