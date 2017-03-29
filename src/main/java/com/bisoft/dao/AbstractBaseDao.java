package com.bisoft.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bisoft.dao.interfaces.GenericDao;

public abstract class AbstractBaseDao<PK extends Serializable, T> implements GenericDao<PK, T> {

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

	public T getByKey(PK key) {
		try {
			return (T) getSession().get(persistentClass, key);
		} catch (HibernateException e) {
			return null;
		}
	}

	public Boolean persist(T entity) {
		try {
			getSession().persist(entity);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	public Boolean update(T entity) {
		try {
			getSession().update(entity);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	public Boolean delete(T entity) {
		try {
			getSession().delete(entity);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	public List<T> list() {
		try {
			@SuppressWarnings("unchecked")
			List<T> list = this.createEntityCriteria().list();
			return list;
		} catch (HibernateException e) {
			return null;
		}
	}
}