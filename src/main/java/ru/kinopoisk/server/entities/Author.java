package ru.kinopoisk.server.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "AUTHOR")
@NamedQuery(name="Author.findAll", query="SELECT e FROM Author e")
public class Author extends LongIdEntity {

    @Column(name = "NAME",nullable = false)
    private String authorName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Article> articles;

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return getId() + " " + authorName+"\n";
    }
}
