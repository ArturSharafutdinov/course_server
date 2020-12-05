package ru.kinopoisk.server.persistence.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="GENRES")
public class Genre extends LongIdEntity{

    @Column(name = "NAME",nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name="games_genres",
            joinColumns=@JoinColumn(name="genres_id"),
            inverseJoinColumns=@JoinColumn(name="game_id"))
    private Set<Game> games = new HashSet<>();

    public Genre(String name, Set<Game> games) {
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
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
