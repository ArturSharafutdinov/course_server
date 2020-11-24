package ru.kinopoisk.server.persistence.dto;

import java.util.Date;
import java.util.List;

public class GameDto extends LongIdEntityDto{

    private String originalName;

    private Date releaseDate;

    private String alterName;

    private String gamesSeries;

    private String description;

    private String imageLink;

    private String Link;

    private List<Integer> rating; // Igromania, metacritic, users

    private List<String> developers;

    private List<String> publishers;

    private List<String> platforms;

    private List<String> genres;

    private List<String> stores;

    public GameDto(String originalName, Date releaseDate, String alterName, String gamesSeries, String description, String imageLink, String link, List<Integer> rating, List<String> developers, List<String> publishers, List<String> platforms, List<String> genres, List<String> stores) {
        this.originalName = originalName;
        this.releaseDate = releaseDate;
        this.alterName = alterName;
        this.gamesSeries = gamesSeries;
        this.description = description;
        this.imageLink = imageLink;
        Link = link;
        this.rating = rating;
        this.developers = developers;
        this.publishers = publishers;
        this.platforms = platforms;
        this.genres = genres;
        this.stores = stores;
    }


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

    public String getAlterName() {
        return alterName;
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
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

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
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

    public List<String> getStores() {
        return stores;
    }

    public void setStores(List<String> stores) {
        this.stores = stores;
    }


    @Override
    public String toString() {
        return "GameDto{" +
                "originalName='" + originalName + '\'' +
                ", releaseDate=" + releaseDate +
                ", alterName='" + alterName + '\'' +
                ", gamesSeries='" + gamesSeries + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", Link='" + Link + '\'' +
                ", rating=" + rating +
                ", developers=" + developers +
                ", publishers=" + publishers +
                ", platforms=" + platforms +
                ", genres=" + genres +
                ", stores=" + stores +
                "} ";
    }
}
