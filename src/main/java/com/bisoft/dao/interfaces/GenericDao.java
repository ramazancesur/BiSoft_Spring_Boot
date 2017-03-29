package com.bisoft.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.bisoft.entities.BaseEntity;

public interface GenericDao<PK extends Serializable, T extends BaseEntity> {
	T getByKey(PK key);

	Boolean persist(T entity);

	Boolean update(T entity);

	Boolean delete(T entity);

	List<T> list();
}
