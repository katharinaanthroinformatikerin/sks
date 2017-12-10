package com.schallerl.movie;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "Actor.checkDependencyActors",
                query = "SELECT a FROM Actor a WHERE a.lastname = :lastname " + "AND a.firstname = :firstname"
        ),
        @NamedQuery(
                name = "Actor.selectAll",
                query = "SELECT a FROM Actor a"
        )
})
@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    private String lastname;

    @XmlAttribute
    private String firstname;

    @XmlAttribute
    private Sex sex;

    @XmlAttribute
    @XmlJavaTypeAdapter(DateAdapter.class)
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
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {

        this.birthdate = birthdate;
    }
}
