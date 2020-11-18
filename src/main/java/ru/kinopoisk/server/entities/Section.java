package ru.kinopoisk.server.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SECTION")
@NamedQuery(name="Section.findAll", query="SELECT e FROM Section e")
public class Section extends LongIdEntity {

    @Column(name = "NAME",nullable = false)
    private String sectionName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private Set<Article> articles;

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    @Override
    public String toString() {
        return getId() + " " + sectionName+"\n";
    }
}
