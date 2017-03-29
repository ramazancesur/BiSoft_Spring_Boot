package com.bisoft.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {
	T getByKey(PK key);

	Boolean persist(T entity);

	Boolean update(T entity);

	Boolean delete(T entity);

	List<T> list();
}
