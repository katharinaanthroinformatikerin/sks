package com.schallerl.movie;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
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
    public Studio create(Studio studio) {
        em.persist(studio);
        return studio;
    }

    @RolesAllowed("MSWrite")
    public void update(Studio studioBeforeUpdate, Studio updateStudio){
        studioBeforeUpdate.setName(updateStudio.getName());
        studioBeforeUpdate.setPostcode(updateStudio.getPostcode());
        studioBeforeUpdate.setCountrycode(updateStudio.getCountrycode());
        em.merge(studioBeforeUpdate);
    }

    @RolesAllowed({"MSRead", "MSWrite"})
    public Studio find(Long studioId) {
        return em.find(Studio.class, studioId);
    }

    @RolesAllowed("MSWrite")
    public void remove(Studio studio){
        em.remove(studio);
    }
}
