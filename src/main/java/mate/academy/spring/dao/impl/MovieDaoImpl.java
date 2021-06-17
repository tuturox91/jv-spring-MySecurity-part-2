package mate.academy.spring.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.MovieDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    public MovieDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Movie WHERE id = :id", Movie.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie, id: " + id, e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = factory.openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("FROM Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movies from db", e);
        }
    }
}
