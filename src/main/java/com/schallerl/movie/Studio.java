package com.schallerl.movie;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Studio.checkDependencyStudio", query = "SELECT s FROM Studio s WHERE s.name = :studioName")
@Entity
@Table(name = "studio")
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "studio")
    private Set<Film> films;

    @ManyToMany
    @JoinTable(
            name="studio_actor",
            joinColumns=@JoinColumn(name="studio_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_studio_actor_studio")),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_studio_actor_actor")))
    private Set<Actor> actors;

    public Studio(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
