package com.schallerl.movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class MovieService {

    private static final Logger log = Logger.getLogger("MovieService");
    @PersistenceContext
    private EntityManager em;

    public MovieService(){}

    public List<Movie> getAllMovies() {
        return em.createNamedQuery("Movie.selectAll", Movie.class)
                .getResultList();
    }

    public List<Movie> searchByTitleParts(String titleParts){
        TypedQuery<Movie> q = em.createNamedQuery("Movie.searchByTitleParts", Movie.class);
        //setting search parameter:
        q.setParameter("titleParts", "%" + titleParts + "%");
        System.out.println("MovieService: searchByTitleParts()");
        log.info("MovieService");
        List<Movie> movies = q.getResultList();
        System.out.println("size of movielist " + movies.size());
        return movies;
    }

    //all of the movies - or none - have to be inserted
    @Transactional
    public String importMovies(List<Movie> moviesToImport) {
        log.info("in method importMovies");

        for(Movie movie : moviesToImport){
            //check, if actors and studio are stored in db:
            //if they are, the films get stored too, otherwise an exception
            // is thrown and the import is aborted

            if(movie.getStudio().getName() == null){
                log.info("no studio");
                throw new EntityNotFoundException();
            } else {
                //Checking if the studio is already stored in the db:
                Studio studio = movie.getStudio();
                String studioName = studio.getName();
                log.info("Looking for studio named " + studioName);
                TypedQuery<Studio> q1 = em.createNamedQuery("Studio.checkDependencyStudio", Studio.class);
                q1.setParameter("studioName", studioName);
                Studio storedStudio = q1.getSingleResult();
                studio.setId(storedStudio.getId());

                if(movie.getActorList() == null){
                    log.info("no actors");
                    throw new EntityNotFoundException();
                } else {
                    List<Actor> playingActors = movie.getActorList();
                    for (Actor actor : playingActors) {
                        if((actor.getFirstname() == null) || (actor.getLastname() == null)){
                            log.info("firstname and/or lastname are missing.");
                            throw new EntityNotFoundException();
                        } else {
                            log.info("looking for actor named " + actor.getLastname() + " " + actor.getFirstname());
                            String lastname = actor.getLastname();
                            String firstname = actor.getFirstname();
                            TypedQuery<Actor> q2 = em.createNamedQuery("Actor.checkDependencyActors", Actor.class);
                            q2.setParameter("lastname", lastname);
                            q2.setParameter("firstname", firstname);

                            //Query throws an EntityNotfound exception if an actor isn't stored in db. In that case,
                            // the whole transaction is aborted and the list of films is not stored in the db:
                            Actor storedActor = q2.getSingleResult();
                            actor.setId(storedActor.getId());
                        }
                    }
                }
            }
            //inserting movie into db.
            em.persist(movie);
        }
        return "Movie(s) imported.";
    }
}
