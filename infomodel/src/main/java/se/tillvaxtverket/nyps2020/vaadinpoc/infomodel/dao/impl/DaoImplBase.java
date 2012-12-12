/*
 * User: joel
 * Date: 2012-11-21
 * Time: 00:47
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.dao.impl;

import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class DaoImplBase implements Dao, Serializable {
    @PersistenceContext(unitName = "nypspersistence")
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public <E> E merge(Class<E> cls, E entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <E> void persist(Class<E> cls, E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void flush()  {
        entityManager.flush();
    }

    @Override
    public <E> List<E> findAll(Class<E> cls, int first, int max) {
        CriteriaQuery<E> query =
                getEntityManager().getCriteriaBuilder().createQuery(cls);
        return getEntityManager()
                .createQuery(query.select(query.from(cls)))
                .setFirstResult(first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public <E> E find(Class<E> cls, long id) {
        entityManager.find(cls, id);
        return null;
    }

    @Override
    public <E> List<E> find(Class<E> cls,
                        CriteriaQuery<E> criteriaQuery,
                        int first,
                        int max) {
        return getEntityManager()
                .createQuery(criteriaQuery)
                .setFirstResult(first)
                .setMaxResults(max)
                .getResultList();
    }

}
