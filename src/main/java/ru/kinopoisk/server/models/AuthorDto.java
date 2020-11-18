package ru.kinopoisk.server.models;

import ru.kinopoisk.server.entities.LongIdEntity;

public class AuthorDto extends LongIdEntityDto {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
