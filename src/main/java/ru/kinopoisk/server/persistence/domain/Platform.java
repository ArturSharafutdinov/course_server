package ru.kinopoisk.server.persistence.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PLATFORMS")
public class Platform extends LongIdEntity{

    @Column(name = "NAME",nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name="games_platforms",
            joinColumns=@JoinColumn(name="platforms_id"),
            inverseJoinColumns=@JoinColumn(name="game_id"))
    private Set<Game> games = new HashSet<>();

    public Platform() {

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

    public Platform(String name, Set<Game> games) {
        this.name = name;
        this.games = games;
    }

    public Platform(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "name='" + name + '\'' +
                "} ";
    }
}
