package ru.course.server.persistence.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    @JsonProperty("code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
