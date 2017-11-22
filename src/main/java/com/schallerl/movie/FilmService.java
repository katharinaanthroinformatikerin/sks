package com.schallerl.movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
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
            //check, if actors and studio are stored in db:
            //if they are, the films get stored too, otherwise an exception
            // is thrown and the import is aborted

            //Checking if the studio is already stored in the db:
            Studio studio = film.getStudio();
            String studioName = studio.getName();
            TypedQuery<Studio> q1 = em.createNamedQuery("Studio.checkDependencyStudio", Studio.class);
            q1.setParameter("studioName", studioName);
            Studio storedStudio = q1.getSingleResult();

            Set<Actor> playingActors = film.getActors();
            for (Actor actor : playingActors) {
                String surname = actor.getSurname();
                String firstName = actor.getFirstName();
                TypedQuery<Actor> q2 = em.createNamedQuery("Actor.checkDependencyActors", Actor.class);
                q2.setParameter("surname", surname);
                q2.setParameter("firstName", firstName);

                //Query throws an EntityNotfound exception if an actor isn't stored in db. In that case,
                // the whole transaction is aborted and the list of films is not stored in the db:
                Actor storedActor = q2.getSingleResult();
            }
            //inserting film into db.
            em.persist(film);
        }
        return "Films imported.";
    }
}
