package com.schallerl.movie;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Film.searchByTitleParts", query = "SELECT f FROM Film f WHERE f.title LIKE :titleParts")
@Entity
@Table(name = "film")
public class Film {
    //creates an id at db-level
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String title;
    private Date releaseDate;
    @Column(length=1024)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="studio_id", foreignKey = @ForeignKey(name="fk_film_studio"))//foreign key, I choose the name of the column. foreignKey naming: 1.) which table and 2.) reference to what
    private Studio studio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="film_actor",
            joinColumns=@JoinColumn(name="film_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_film_actor_film")),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_film_actor_actor")))
    private Set<Actor> actors;

    public Film(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}