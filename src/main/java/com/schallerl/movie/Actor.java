package com.schallerl.movie;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Actor.checkDependencyActors",
        query = "SELECT a FROM Actor a WHERE a.surname = :surname " +
                "AND a.firstName = :firstName")
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String firstName;
    private int sex;
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "actors")
    private Set<Studio> studios;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films;

    public Actor(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Studio> getStudios() {
        return studios;
    }

    public void setStudios(Set<Studio> studios) {
        this.studios = studios;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
