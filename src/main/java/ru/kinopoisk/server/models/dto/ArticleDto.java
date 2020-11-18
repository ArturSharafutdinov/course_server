package ru.kinopoisk.server.models.dto;

import java.util.Date;


public class ArticleDto extends LongIdEntityDto {

    private String name;// Name of the article

    private String author;// Author that posted article

    private Date release;// Release date

    private String smallDescription; // Description on the preview block of the article

    private String fullDescription; // Content of the article

    private int views; // Number of views

    private String type; // Section in which the article is located

    private String link; // Link of the full article page


    public ArticleDto(String name, String author, Date release, String smallDescription, String fullDescription, int views, String type, String link) {
        this.name = name;
        this.author = author;
        this.release = release;
        this.smallDescription = smallDescription;
        this.fullDescription = fullDescription;
        this.views = views;
        this.type = type;
        this.link = link;
    }

    public ArticleDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", release=" + release +
                ", smallDescription='" + smallDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", views=" + views +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
