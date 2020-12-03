package ru.kinopoisk.server.persistence.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="GENRES")
public class Genre extends LongIdEntity{

    @Column(name = "NAME",nullable = false)
    private String name;

    @ManyToMany
    private List<Game> games;

    public Genre(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }

    public Genre() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                "} ";
    }
}
