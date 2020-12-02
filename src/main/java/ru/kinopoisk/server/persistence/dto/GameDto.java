package ru.kinopoisk.server.persistence.dto;

import java.util.Date;
import java.util.List;

public class GameDto extends LongIdEntityDto{

    private String originalName;

    private Date releaseDate;

    private String gamesSeries;

    private String description;

    private String imageLink;

    private String Link;

    private Integer metacriticRating; // Рейтинг метакритика

    private List<String> developers;  // Разработчики

    private List<String> platforms; // Платформы

    private List<String> genres; // Жанры

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(Integer metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "originalName='" + originalName + '\'' +
                ", releaseDate=" + releaseDate +
                ", gamesSeries='" + gamesSeries + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", Link='" + Link + '\'' +
                ", developers=" + developers +
                ", platforms=" + platforms +
                ", genres=" + genres +
                "} ";
    }

    public GameDto(String originalName, Date releaseDate, String gamesSeries, String description, String imageLink, String link, Integer metacriticRating, List<String> developers, List<String> platforms, List<String> genres) {
        this.originalName = originalName;
        this.releaseDate = releaseDate;
        this.gamesSeries = gamesSeries;
        this.description = description;
        this.imageLink = imageLink;
        Link = link;
        this.metacriticRating = metacriticRating;
        this.developers = developers;
        this.platforms = platforms;
        this.genres = genres;
    }

    public GameDto(){

    }
}
