package mate.academy.spring.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.CinemaHallDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.CinemaHall;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {

    public CinemaHallDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Optional<CinemaHall> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM CinemaHall WHERE id = :id", CinemaHall.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie, id: " + id, e);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = factory.openSession()) {
            Query<CinemaHall> getAllCinemaHallsQuery =
                    session.createQuery("FROM CinemaHall", CinemaHall.class);
            return getAllCinemaHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all cinema halls from db", e);
        }
    }
}
