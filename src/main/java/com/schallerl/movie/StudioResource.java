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

@Path("/studio")
@SecurityDomain("MovieSD")
public class StudioResource {
    @PersistenceContext
    private EntityManager em;
    @Context
    private UriInfo uriInfo;
    @Inject
    private StudioService studioService;

    @POST
    @RolesAllowed("MSWrite")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Studio studio) {
        //em.persist(studio);
        Studio createdStudio = studioService.createStudio(studio);
        URI uri = uriInfo.getAbsolutePathBuilder().path(createdStudio.getId().toString()).build();
        return Response.created(uri).build();
    }

    @GET
    @RolesAllowed({"MSRead", "MSWrite"})
    @Path("/{studioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Studio retrieveAsJSONXML(@PathParam("studioId") Long studioId) {
        return em.find(Studio.class, studioId);
    }

    @PUT
    @RolesAllowed("MSWrite")
    @Path("/{studioId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("studioId") Long studioId, Studio studio) {
        Studio studioBeforeUpdate = em.find(Studio.class, studioId);
        if (studioBeforeUpdate != null) {
            studioBeforeUpdate.setName(studio.getName());
            studioBeforeUpdate.setPostcode(studio.getPostcode());
            studioBeforeUpdate.setCountrycode(studio.getCountrycode());
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @RolesAllowed("MSWrite")
    @Transactional
    @Path("/{studioId}")
    public void delete(@PathParam("studioId") Long studioId) {
        Studio studio = em.find(Studio.class, studioId);
        if (studio != null) {
            em.remove(studio);
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @RolesAllowed({"MSRead", "MSWrite"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Studio> getAll() {
        return studioService.getAllStudios();
    }
}
