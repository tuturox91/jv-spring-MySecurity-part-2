package mate.academy.spring.service;

import mate.academy.spring.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();
}
