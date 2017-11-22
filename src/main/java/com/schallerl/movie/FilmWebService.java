package com.schallerl.movie;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebService
public class FilmWebService {

    private static final Logger log = Logger.getLogger("FilmWebService");

    @Inject
    private FilmService filmservice;

    public FilmWebService(){}

    //jede Methode, die ich nach außen hin bekannt machen will, mit Annotation WebMethod versehen!
    @WebMethod
    public List<Film> searchFilms(String titleParts){
        System.out.println("FilmWebService: searchFilms()");
        return filmservice.searchByTitleParts(titleParts);
    }

    @WebMethod
    public String importFilms(List<Film> filmsToImport){
        log.info("in Methode importFilms()." + filmsToImport.size());
        return filmservice.importFilms(filmsToImport);
    }
}
