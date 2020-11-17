package ru.kinopoisk.server.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.kinopoisk.server.models.LongIdEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "AUTHOR")
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
}
