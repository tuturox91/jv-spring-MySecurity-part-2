package mate.academy.spring.dao;

import mate.academy.spring.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;

    public AbstractDao(SessionFactory factory) {
        this.factory = factory;
    }

    public T add(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + t.getClass().getSimpleName() + " " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
