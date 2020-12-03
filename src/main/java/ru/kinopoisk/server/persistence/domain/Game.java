package ru.kinopoisk.server.persistence.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="GAMES")
public class Game extends LongIdEntity{

    @Column(name="ORIGINAL_NAME",nullable = false,length = 250)
    private String originalName;

    @Column(name="RELEASE_DATE",length = 30)
    private String releaseDate;

    @Column(name="SERIES",length = 100)
    private String gamesSeries;

    @Column(name="DESCRIPTION",length = 5000)
    private String description;

    @Column(name="IMAGE_LINK",length = 1000)
    private String imageLink;

    @Column(name="LINK",nullable = false,length = 100)
    private String Link;

    @Column(name="RATING",nullable = false)
    private Integer metacriticRating; // Рейтинг метакритика

    @ManyToMany
    private List<Developer> developers;  // Разработчики

    @ManyToMany
    private List<Platform> platforms; // Платформы

    @ManyToMany
    private List<Genre> genres; // Жанры


    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGamesSeries() {
        return gamesSeries;
    }

    public void setGamesSeries(String gamesSeries) {
        this.gamesSeries = gamesSeries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Integer getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(Integer metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Game{" +
                "originalName='" + originalName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", gamesSeries='" + gamesSeries + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", Link='" + Link + '\'' +
                ", metacriticRating=" + metacriticRating +
                ", developers=" + developers +
                ", platforms=" + platforms +
                ", genres=" + genres +
                "} ";
    }
}
