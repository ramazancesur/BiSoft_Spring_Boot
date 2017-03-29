package com.bisoft.dao;

import org.springframework.stereotype.Repository;

import com.bisoft.dao.interfaces.IKonusmaciDao;
import com.bisoft.entities.Konusmaci;

@Repository("KONUSMACI_DAO")
public class KonusmaciDao extends AbstractBaseDao<Long, Konusmaci> implements IKonusmaciDao {
	@Override
	public Boolean persist(Konusmaci entity) {
		// TODO Auto-generated method stub

		return super.persist(entity);
	}
}