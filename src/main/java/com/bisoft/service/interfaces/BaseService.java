package com.bisoft.service.interfaces;

import java.util.List;

import com.bisoft.entities.BaseEntity;

public interface BaseService<T extends BaseEntity> {
	List<T> list();

	T findById(Long id);

	Boolean save(T entity);

	Boolean update(T entity);

	Boolean deleteById(Long id);
}