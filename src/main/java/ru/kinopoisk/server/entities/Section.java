package ru.kinopoisk.server.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.kinopoisk.server.models.LongIdEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SECTION")
public class Section extends LongIdEntity {

    @Column(name = "NAME",nullable = false)
    private String sectionName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private Set<Article> articles;
}
