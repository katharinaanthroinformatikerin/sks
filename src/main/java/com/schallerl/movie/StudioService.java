package com.schallerl.movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudioService {
    private static final Logger log = Logger.getLogger("StudioService");
    @PersistenceContext
    private EntityManager em;

    public StudioService(){}

    public List<Studio> getAllStudios() {
        return em.createNamedQuery("Studio.selectAll", Studio.class)
                .getResultList();
    }
}
