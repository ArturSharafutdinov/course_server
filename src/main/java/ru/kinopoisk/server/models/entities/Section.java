package ru.kinopoisk.server.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SECTION")
@NamedQuery(name="Section.findAll", query="SELECT e FROM Section e")
@NamedQuery(name="Section.findByName", query="SELECT e FROM Section e where e.sectionName=?1")
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
