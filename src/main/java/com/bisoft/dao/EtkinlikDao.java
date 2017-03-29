package com.bisoft.dao;

import org.springframework.stereotype.Repository;

import com.bisoft.dao.interfaces.IEtkinlikDao;
import com.bisoft.entities.Etkinlik;

@Repository("ETKINLIK_DAO")
public class EtkinlikDao extends AbstractBaseDao<Long, Etkinlik> implements IEtkinlikDao {

	@Override
	public boolean hatirlaticiMailGonder() {
		// TODO Auto-generated method stub
		return false;
	}

}