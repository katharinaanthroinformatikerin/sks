package com.schallerl.movie;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
}
