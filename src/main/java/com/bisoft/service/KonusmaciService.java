package com.bisoft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bisoft.dao.interfaces.IKonusmaciDao;
import com.bisoft.entities.Konusmaci;
import com.bisoft.service.interfaces.IKonusmaciService;

@Transactional
@Service("KONUSMACI_SERVICE")
public class KonusmaciService implements IKonusmaciService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KonusmaciService.class);

	@Autowired
	private IKonusmaciDao konusmaciDao;

	@Override
	public List<Konusmaci> list() {
		// TODO Auto-generated method stub
		return konusmaciDao.list();
	}

	@Override
	public Konusmaci findById(Long id) {
		// TODO Auto-generated method stub
		return konusmaciDao.getByKey(id);
	}

	@Override
	public Boolean save(Konusmaci entity) {
		// TODO Auto-generated method stub
		return konusmaciDao.persist(entity);
	}

	@Override
	public Boolean update(Konusmaci entity) {
		// TODO Auto-generated method stub
		return konusmaciDao.update(entity);
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		Konusmaci konusmaci = konusmaciDao.getByKey(id);
		if (konusmaci != null) {
			return konusmaciDao.delete(konusmaci);
		} else {
			LOGGER.error("Error 101: hatalı konusmaci");
			return false;
		}
	}

	@Override
	public Boolean sendMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

}
