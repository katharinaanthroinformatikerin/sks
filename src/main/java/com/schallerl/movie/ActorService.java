package com.schallerl.movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ActorService {
    private static final Logger log = Logger.getLogger("ActorService");
    @PersistenceContext
    private EntityManager em;

    public ActorService(){}

    public List<Actor> getAllActors() {
        return em.createNamedQuery("Actor.selectAll", Actor.class)
                .getResultList();
    }
}
