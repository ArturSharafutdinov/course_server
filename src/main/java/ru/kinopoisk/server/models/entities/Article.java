package ru.kinopoisk.server.models.entities;

import javax.persistence.*;
import java.util.Date;
//asas
@Entity
@Table(name="ARTICLE")
@NamedQuery(name="Article.findAll", query="SELECT e FROM Article e")
@NamedQuery(name="Article.findByName", query="SELECT e FROM Article e WHERE e.name = :name")
public class Article extends LongIdEntity{
    @Column(name="NAME",nullable = false)
    private String name;

    @Column(name="RELEASED",nullable = false)
    private Date release;

    @Column(name="SMALLDESC")
    private String smallDescription;

    @Column(name="FULLDESC")
    private String fullDescription;

    @Column(name="VIEWS")
    private int views;

    @Column(name="LINK")
    private String link;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SECTION_ID", nullable = false)
    private Section section;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", release=" + release +
                ", smallDescription='" + smallDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", views=" + views +
                ", link='" + link + '\'' +
                '}';
    }
}
