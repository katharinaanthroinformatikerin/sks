package com.schallerl.movie;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@SecurityDomain("MovieSD")
public class ActorService {
    private static final Logger log = Logger.getLogger("ActorService");
    @PersistenceContext
    private EntityManager em;

    public ActorService(){}

    @RolesAllowed({"MSWrite", "MSRead"})
    public List<Actor> getAllActors() {
        return em.createNamedQuery("Actor.selectAll", Actor.class)
                .getResultList();
    }

    @RolesAllowed("MSWrite")
    public Actor save(Actor actor) {
        em.persist(actor);
        return actor;
    }

    @RolesAllowed({"MSWrite", "MSRead"})
    public Actor find(Long actorId) {
        return em.find(Actor.class, actorId);
    }

    @RolesAllowed("MSWrite")
    public void update(Actor actorBeforeUpdate, Actor actor) {
        actorBeforeUpdate.setFirstname(actor.getFirstname());
        actorBeforeUpdate.setLastname(actor.getLastname());
        actorBeforeUpdate.setSex(actor.getSex());
        actorBeforeUpdate.setBirthdate(actor.getBirthdate());
        em.merge(actorBeforeUpdate);
    }

    @RolesAllowed("MSWrite")
    public void remove(Actor actor) {
        em.remove(actor);
    }
}
