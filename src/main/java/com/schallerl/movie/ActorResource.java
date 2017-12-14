package com.schallerl.movie;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
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

@Path("/actor")
@Transactional
@SecurityDomain("MovieSD")
public class ActorResource {

    @PersistenceContext
    private EntityManager em;
    @Context
    private UriInfo uriInfo;
    @Inject
    private ActorService actorService;

    @POST
    @RolesAllowed("MSWrite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Actor actor) {
        em.persist(actor);
        URI uri = uriInfo.getAbsolutePathBuilder().path(actor.getId().toString()).build();
        return Response.created(uri).build();
    }

    @GET
    @RolesAllowed({"MSRead", "MSWrite"})
    @Path("/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Actor retrieveAsJSONXML(@PathParam("actorId") Long actorId) {
        return em.find(Actor.class, actorId);
    }

    @PUT
    @RolesAllowed("MSWrite")
    @Path("/{actorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("actorId") Long actorId, Actor actor) {
        Actor actorBeforeUpdate = em.find(Actor.class, actorId);
        if (actorBeforeUpdate != null) {
            actorBeforeUpdate.setFirstname(actor.getFirstname());
            actorBeforeUpdate.setLastname(actor.getLastname());
            actorBeforeUpdate.setSex(actor.getSex());
            actorBeforeUpdate.setBirthdate(actor.getBirthdate());
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @RolesAllowed("MSWrite")
    @Path("/{actorId}")
    public void delete(@PathParam("actorId") Long actorId) {
        Actor actor = em.find(Actor.class, actorId);
        if (actor != null) {
            em.remove(actor);
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @RolesAllowed({"MSRead", "MSWrite"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAll() {
        return actorService.getAllActors();
    }
}
