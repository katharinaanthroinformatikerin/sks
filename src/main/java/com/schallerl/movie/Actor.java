package com.schallerl.movie;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
}
