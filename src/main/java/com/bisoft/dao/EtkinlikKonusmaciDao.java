package com.bisoft.dao;

import org.springframework.stereotype.Repository;

import com.bisoft.dao.interfaces.IEtkinlikKonusmaciRelDao;
import com.bisoft.entities.EtkinlikKonusmaciRel;

@Repository("ETKINLIK_KONUSMACI_DAO")
public class EtkinlikKonusmaciDao extends AbstractBaseDao<Long, EtkinlikKonusmaciRel>
		implements IEtkinlikKonusmaciRelDao {

}
