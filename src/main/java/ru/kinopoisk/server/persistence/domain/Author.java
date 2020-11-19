package ru.kinopoisk.server.persistence.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
@NamedQuery(name="Author.findAll", query="SELECT e FROM Author e")
@NamedQuery(name="Author.findByName", query="SELECT e FROM Author e where e.authorName=?1")
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
