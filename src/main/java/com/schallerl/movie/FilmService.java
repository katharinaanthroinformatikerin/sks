package com.schallerl.movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class FilmService {

    private static final Logger log = Logger.getLogger("FilmService");
    @PersistenceContext
    private EntityManager em;

    public FilmService(){}

    public List<Film> searchByTitleParts(String titleParts){
        TypedQuery<Film> q = em.createNamedQuery("Film.searchByTitleParts", Film.class);
        //setting search parameter:
        q.setParameter("titleParts", "%" + titleParts + "%");
        System.out.println("FilmService: searchByTitleParts()");
        log.info("FilmService");
        List<Film> films = q.getResultList();
        System.out.println("Grösse der filmliste " + films.size());
        return films;
    }

    //all of the movies - or none - have to be inserted
    @Transactional
    public String importFilms(List<Film> filmsToImport) {
        log.info("in Methode importFilms");

        for(Film film : filmsToImport){
            //inserting film into db.
            em.persist(film);
        }

        return "ohoh...";
    }
}
