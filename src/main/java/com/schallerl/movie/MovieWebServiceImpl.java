package com.schallerl.movie;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.logging.Logger;

//Defines the class as a webservice endpoint
@WebService(endpointInterface="com.schallerl.movie.MovieWebService", serviceName="MovieWebService", portName="MovieWebServicePort")
public class MovieWebServiceImpl implements MovieWebService {

    private static final Logger log = Logger.getLogger("MovieWebService");

    @Inject
    private MovieService movieservice;

    //The implementation class also must define a default, public, no-argument constructor.
    public MovieWebServiceImpl(){}

    @Override
    public Movies searchFilms(String titleParts){
        System.out.println("MovieWebService: searchMovies() " + titleParts);
        return new Movies(movieservice.searchByTitleParts(titleParts));
    }

    @Override
    public String importMovies(Movies moviesToImport){
        log.info("in Methode importMovies()." + moviesToImport.getMovies().size());
        return movieservice.importMovies(moviesToImport.getMovies());
    }
}
