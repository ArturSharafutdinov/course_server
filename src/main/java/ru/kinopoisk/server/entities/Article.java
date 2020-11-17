package ru.kinopoisk.server.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.kinopoisk.server.models.LongIdEntity;

import javax.persistence.*;
import java.util.Date;
//asas
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="ARTICLE")
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


}
