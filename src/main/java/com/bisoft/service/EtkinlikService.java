package com.bisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bisoft.dao.interfaces.IEtkinlikDao;
import com.bisoft.dao.interfaces.IEtkinlikKonusmaciRelDao;
import com.bisoft.entities.Etkinlik;
import com.bisoft.entities.Konusmaci;
import com.bisoft.service.interfaces.IEtkinlikService;

@Transactional
@Service("ETKINLIK_SERVICE")
public class EtkinlikService implements IEtkinlikService {

	@Autowired
	private IEtkinlikDao etkinlikDao;

	@Autowired
	private IEtkinlikKonusmaciRelDao etkinlikKullaniciDao;

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

	@Override
	public Boolean insertEtkinlikKonusma(List<Etkinlik> lstEtkinlik, Konusmaci konusmaci) {
		// TODO Auto-generated method stub
		return etkinlikKullaniciDao.insertEtkinlikKonusma(lstEtkinlik, konusmaci);
	}

	@Override
	public Boolean insertEtkinlikKonusma(Etkinlik etkinlik, List<Konusmaci> lstKonusmaci) {
		// TODO Auto-generated method stub
		return etkinlikKullaniciDao.insertEtkinlikKonusma(etkinlik, lstKonusmaci);
	}

	@Override
	public List<Konusmaci> getEtkinliktekiKonusmacilar(Long etkinlikID) {
		// TODO Auto-generated method stub
		return etkinlikKullaniciDao.getEtkinliktekiKonusmacilar(etkinlikID);
	}

	@Override
	public List<Etkinlik> getKonusmaciEtkinlik(Long konusmaciID) {
		// TODO Auto-generated method stub
		return etkinlikKullaniciDao.getKonusmaciEtkinlik(konusmaciID);
	}

}
