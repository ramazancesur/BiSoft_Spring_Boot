package com.bisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bisoft.dao.interfaces.IEtkinlikDao;
import com.bisoft.entities.Etkinlik;
import com.bisoft.service.interfaces.IEtkinlikService;

@Transactional
@Service
public class EtkinlikService implements IEtkinlikService {

	@Autowired
	private IEtkinlikDao etkinlikDao;

	@Override
	public List<Etkinlik> list() {
		// TODO Auto-generated method stub
		return etkinlikDao.list();
	}

	@Override
	public Etkinlik findById(Long id) {
		// TODO Auto-generated method stub
		return etkinlikDao.getByKey(id);
	}

	@Override
	public Boolean save(Etkinlik entity) {
		// TODO Auto-generated method stub
		return etkinlikDao.persist(entity);
	}

	@Override
	public Boolean update(Etkinlik entity) {
		// TODO Auto-generated method stub
		return etkinlikDao.update(entity);
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		Etkinlik etkinlik = etkinlikDao.getByKey(id);
		if (etkinlik == null) {
			return false;
		} else {
			etkinlikDao.delete(etkinlik);
			return true;
		}
	}

	@Override
	public Boolean hatirlaticiMailGonder(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
