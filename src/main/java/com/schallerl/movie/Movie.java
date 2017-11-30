package com.schallerl.movie;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "Movie.searchByTitleParts", query = "SELECT f FROM Movie f WHERE f.title LIKE :titleParts")
@Entity
@Table(name = "movie")
public class Movie {
    //creates an id at db-level
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlAttribute
    private String title;

    @XmlAttribute(name="releaseyear")
    private Integer releaseYear;

    @XmlAttribute
    @Column(length=1024)
    private String description;

    @XmlAttribute
    private Genre genre;

    @XmlAttribute
    private Integer length;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="studio_id", foreignKey = @ForeignKey(name="fk_movie_studio"))//foreign key, I choose the name of the column. foreignKey naming: 1.) which table and 2.) reference to what
    private Studio studio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="movie_actor",
            joinColumns=@JoinColumn(name="movie_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_movie_actor_film")),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_movie_actor_actor")))
    @XmlElementWrapper(name="actors")
    @XmlElement(name="actor")
    private List<Actor> actorList;

    public Movie(){}

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

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actors) {
        this.actorList = actors;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}