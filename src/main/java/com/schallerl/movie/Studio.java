package com.schallerl.movie;

import javax.persistence.*;
import java.util.Set;

@NamedQuery(name = "Studio.checkDependencyStudio", query = "SELECT s FROM Studio s WHERE s.name = :studioName")
@Entity
@Table(name = "studio")
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String countrycode;
    private String postcode;

    /*@OneToMany(mappedBy = "studio")
    private Set<Movie> movies;
*/
    /*@ManyToMany
    @JoinTable(
            name="studio_actor",
            joinColumns=@JoinColumn(name="studio_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_studio_actor_studio")),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_studio_actor_actor")))
    private Set<Actor> actors;*/

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

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String street) {
        this.countrycode = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String zipcode) {
        this.postcode = zipcode;
    }
/*
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }*/
}
