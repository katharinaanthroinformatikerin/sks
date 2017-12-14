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
public class StudioService {
    private static final Logger log = Logger.getLogger("StudioService");
    @PersistenceContext
    private EntityManager em;

    public StudioService(){}

    @RolesAllowed({"MSRead", "MSWrite"})
    public List<Studio> getAllStudios() {
        return em.createNamedQuery("Studio.selectAll", Studio.class)
                .getResultList();
    }

    @RolesAllowed("MSWrite")
    public Studio createStudio(Studio studio) {
        em.persist(studio);
        return studio;
    }
}
