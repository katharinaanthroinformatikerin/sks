package com.schallerl.movie;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MovieWebService {

    //Business methods that are exposed to web service clients must be annotated with javax.jws.WebMethod
    @WebMethod
    public @WebResult(name = "movies") Movies searchFilms(String titleParts);

    @WebMethod
    public String importMovies(Movies moviesToImport);
}

