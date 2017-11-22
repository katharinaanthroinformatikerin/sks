package com.schallerl.movie;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Actor.checkDependencyActors",
        query = "SELECT a FROM Actor a WHERE a.lastname = :lastname " +
                "AND a.firstname = :firstname")
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String firstname;
    private Sex sex;
    private Date birthdate;

    public Actor(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String surname) {
        this.lastname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date dateOfBirth) {
        this.birthdate = dateOfBirth;
    }
}
