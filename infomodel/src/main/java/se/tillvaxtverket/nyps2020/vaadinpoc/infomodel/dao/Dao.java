/*
 * User: joel
 * Date: 2012-11-22
 * Time: 23:47
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.dao;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface Dao {
    <E> E merge(Class<E> cls, E entity);

    <E> void persist(Class<E> cls, E entity);

    void flush();

    <E> List<E> findAll(Class<E> cls, int first, int max);

    <E> E find(Class<E> cls, long id);

    <E> List<E> find(Class<E> cls,
                     CriteriaQuery<E> criteriaQuery,
                     int first,
                     int max);
}
