package com.schallerl.movie;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "moviecontent")
public class Movies {

    @XmlElementWrapper(name="movies")
    @XmlElement(name="movie")
    private List<Movie> movies;

    public Movies(){}

    public Movies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
