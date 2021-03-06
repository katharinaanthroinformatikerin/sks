package com.schallerl.movie;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/movies")
@Transactional
public class MovieResource {
    //@PersistenceContext
    //private EntityManager em;
    @Context
    private UriInfo uriInfo;
    @Inject
    private MovieService movieService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Movie movie) {
        //em.persist(movie);
        Movie createdMovie = movieService.save(movie);
        URI uri = uriInfo.getAbsolutePathBuilder().path(createdMovie.getId().toString()).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{movieId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Movie retrieveAsJSONXML(@PathParam("movieId") Long movieId) {
        //return em.find(Movie.class, movieId);
        Movie movie = movieService.find(movieId);
        if (movie != null){
            return movie;
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

/*    @GET
    @Path("/{movieId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String retrieveAsString(@PathParam("movieId") Long movieId) {
        Movie movie = em.find(Movie.class, movieId);
        return (movie != null ? movie.toString() : null);
    }
*/
    @PUT
    @Path("/{movieId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void update(@PathParam("movieId") Long movieId, Movie movie) {
        //Movie movieOld = em.find(Movie.class, movieId);
        Movie movieOld = movieService.find(movieId);
        if (movieOld != null) {
            movieService.update(movieOld, movie);
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("/{movieId}")
    public void delete(@PathParam("movieId") Long movieId) {
        //Movie movie = em.find(Movie.class, movieId);
        Movie movie = movieService.find(movieId);

        if (movie != null) {
            //em.remove(movie);
            movieService.remove(movie);
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getAll() {
        return movieService.getAllMovies();
    }
}
