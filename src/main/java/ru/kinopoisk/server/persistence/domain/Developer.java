package ru.kinopoisk.server.persistence.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="DEVELOPERS")
public class Developer extends LongIdEntity{

    @Column(name = "NAME",nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.MERGE
            })
    @JoinTable(name="games_developers",
            joinColumns=@JoinColumn(name="developers_id"),
            inverseJoinColumns=@JoinColumn(name="game_id"))
    private Set<Game> games= new HashSet<>();

    public Developer() {

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

    public Developer(String name, Set<Game> games) {
        this.name = name;
        this.games = games;
    }

    public Developer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                "} ";
    }
}
