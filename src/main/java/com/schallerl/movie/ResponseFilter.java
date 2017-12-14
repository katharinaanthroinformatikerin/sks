package com.schallerl.movie;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider //affects all webservices
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        MultivaluedMap<String, Object> headers = response.getHeaders();
        //instead of *, address of the caller
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "Authorization");
        headers.add("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, POST, PUT");
        //caching deactivated
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        headers.add("Cache-Control", cacheControl);
    }
}
