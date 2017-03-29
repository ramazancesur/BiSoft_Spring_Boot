package com.bisoft.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bisoft.dao.interfaces.GenericDao;
import com.bisoft.entities.BaseEntity;
import com.bisoft.helper.EnumUtil.EntityState;

public abstract class AbstractBaseDao<PK extends Serializable, T extends BaseEntity>
		implements GenericDao<PK, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractBaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(this.persistentClass);
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		try {
			Criteria criteria=createEntityCriteria();
			criteria.add(Restrictions.eq("id",key));
			criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
			return (T) criteria.uniqueResult();
		} catch (HibernateException e) {
			return null;
		}
	}

	public Boolean persist(T entity) {
		try {
			entity.setCreatedDate(new Date());
			entity.setEntityState(EntityState.ACTIVE);
			entity.setUpdatedDate(new Date());
			getSession().persist(entity);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	public Boolean update(T entity) {
		try {
			entity.setUpdatedDate(new Date());
			getSession().merge(entity);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	public Boolean delete(T entity) {
		try {
			entity.setUpdatedDate(new Date());
			entity.setEntityState(EntityState.PASSIVE);
			getSession().merge(entity);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> list() {
		try {
			Criteria criteria=this.createEntityCriteria();
			criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
			criteria.addOrder(Order.asc("createdDate"));
			List<T> list = criteria.list();
			return list;
		} catch (HibernateException e) {
			return null;
		}
	}
}