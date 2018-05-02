/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.common.database.impl;

import com.argusoft.ars.common.database.GenericDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Basic CRUD operations are implemented in the GenericDao interface.
 *
 * @author Harshit
 */
public abstract class BaseAbstractGenericDao<EntityType, IDType extends Serializable> extends HibernateDaoSupport implements GenericDao<EntityType, IDType> {

    private Class<EntityType> persistentClass = (Class<EntityType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    @Qualifier("sessionFactoryARS")
    private void setARSSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    /**
     * get the persistent class
     *
     * @return
     */
    public Class<EntityType> getPersistentClass() {
        return persistentClass;
    }

    /**
     * Retrieve entity by id
     *
     * @param id of type IDType
     * @return Object of type EntityType
     */
    @Override
    public EntityType retrieveById(IDType id) {
        return (EntityType) getHibernateTemplate().get(getPersistentClass(), id);
    }

    /**
     * save the created entity
     *
     * @param entity of type EntityType
     * @return Object of type IDType
     */
    @Override
    public IDType create(EntityType entity) {
        return (IDType) getHibernateTemplate().save(entity);
    }

    /**
     * Save Or Update Entity
     *
     * @param entity of type EntityType
     *
     */
    @Override
    public void createOrUpdate(EntityType entity) {
        getHibernateTemplate().merge(entity);
    }

    /**
     * Update entity
     *
     * @param entity of type EntityType
     */
    @Override
    public void update(EntityType entity) {
        getHibernateTemplate().merge(entity);
    }

    /**
     * Delete created Entity
     *
     * @param entity of type EntityType
     */
    @Override
    public void delete(EntityType entity) {
        getHibernateTemplate().delete(entity);
    }
    
     @Override
    public void deleteAll(List<EntityType> entitys) {
        getHibernateTemplate().deleteAll(entitys);
    }

    /**
     * Delete created Entity by a particular Id
     *
     * @param id of type IDType
     */
    @Override
    public void deleteById(IDType id) {
        EntityType entity = retrieveById(id);
        if (entity != null) {
            delete(entity);
        }
    }

    /**
     * Retrieve all data
     *
     * @return List of type EntityType
     */
    @Override
    public List<EntityType> retrieveAll() {
        List<EntityType> list = getHibernateTemplate().loadAll(getPersistentClass());
        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);
        return list;
    }

    /**
     * Retrieve data in a particular order@p
     *
     * @param orderBy of type String
     * @return List of type EntityType
     */
    @Override
    public List<EntityType> findAll(String orderBy) {

        List<EntityType> list = getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(getPersistentClass()).addOrder(Order.asc(orderBy)));
        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);
        return list;
    }

    @Override
    public List<EntityType> findFiltered(String property, Object filter) {

        List<EntityType> list = getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(getPersistentClass()).add(
                Expression.eq(property, filter)));
        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);
        return list;
    }

    @Override
    public List<EntityType> findFiltered(String property, Object filter, String orderBy) {

        List<EntityType> list = getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(getPersistentClass()).add(
                Expression.eq(property, filter)).addOrder(
                Order.asc(orderBy)));
        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);
        return list;
    }

    @Override
    public List<EntityType> findLikeFiltered(String property, Object filter) {

        return getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(getPersistentClass()).add(
                Expression.ilike(property, filter)));
    }

    @Override
    public List<EntityType> findLikeFiltered(String property, Object filter, String orderBy) {

        return getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(getPersistentClass()).add(
                Expression.ilike(property, filter)).addOrder(
                Order.asc(orderBy)));
    }

    /**
     * Retrieve the data on particular property
     *
     * @param property of type String and filter of type Object
     * @return Object of type EntityType
     */
    @Override
    public EntityType findUniqueFiltered(String property, Object filter) {

        EntityType entityType = null;

        try {
            entityType = (EntityType) DataAccessUtils.requiredUniqueResult(getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(getPersistentClass()).add(
                    Expression.eq(property, filter))));
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(BaseAbstractGenericDao.class.getName()).log(Level.INFO, "Error in findUniqueFiltered...");
        }

        return entityType;
    }

    /**
     * Retrieve the data on particular property and in a particular order
     *
     * @param property of type String , filter of type Object and orderBy of
     * type String
     * @return Object of type EntityType
     */
    @Override
    public EntityType findUniqueFiltered(String property, Object filter, String orderBy) {
        return (EntityType) DataAccessUtils.requiredUniqueResult(getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(getPersistentClass()).add(
                Expression.eq(property, filter)).addOrder(
                Order.asc(orderBy))));
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    public List<EntityType> findByCriteria(Criterion... criterion) {
        DetachedCriteria crit = DetachedCriteria.forClass(getPersistentClass());

        for (Criterion c : criterion) {
            crit.add(c);
        }
        List<EntityType> list = getHibernateTemplate().findByCriteria(crit);

        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);

        return list;
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    public List<EntityType> findByCriteriaList(List<Criterion> criterions) {
        DetachedCriteria crit = DetachedCriteria.forClass(getPersistentClass());

        for (Criterion c : criterions) {
            crit.add(c);
        }

        List<EntityType> list = getHibernateTemplate().findByCriteria(crit);

        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);

        return list;
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    public List<EntityType> findByCriteriaList(List<Criterion> criterions, Boolean isSearch) {
        DetachedCriteria crit = DetachedCriteria.forClass(getPersistentClass());

        for (Criterion c : criterions) {
            crit.add(c);
        }

        List<EntityType> list = null;

        if (isSearch != null && isSearch) {
            list = getHibernateTemplate().findByCriteria(crit, 0, 100);
        } else {
            list = getHibernateTemplate().findByCriteria(crit);
        }

        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);

        return list;
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    public List<EntityType> findByCriteriaWithRange(Criteria criteria) {
        return criteria.list();
    }

    /**
     * Find by example
     *
     * @param entity of type Entity Type
     * @return List of type EntityType
     */
    @Override
    public List<EntityType> findByExample(EntityType entity) {

        List<EntityType> list = getHibernateTemplate().findByExample(entity);
        DistinctRootEntityResultTransformer transformer = DistinctRootEntityResultTransformer.INSTANCE;
        list = transformer.transformList(list);
        return list;
    }

    /**
     * Use this method to get total number of record for given criteria.
     */
    @Override
    public int findTotalCountforCriteria(Criteria criteria) {

        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.list().get(0)).intValue();
    }
}
