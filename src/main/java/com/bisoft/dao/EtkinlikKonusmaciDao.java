package com.bisoft.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bisoft.dao.interfaces.IEtkinlikKonusmaciRelDao;
import com.bisoft.entities.Etkinlik;
import com.bisoft.entities.EtkinlikKonusmaciRel;
import com.bisoft.entities.Konusmaci;
import com.bisoft.helper.EnumUtil.EntityState;

@Repository("ETKINLIK_KONUSMACI_DAO")
public class EtkinlikKonusmaciDao extends AbstractBaseDao<Long, EtkinlikKonusmaciRel>
		implements IEtkinlikKonusmaciRelDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EtkinlikKonusmaciDao.class);

	// ilk olarak konuşmaci ve etkinlik normal olarak eklenecek
	// daha sonra ise Salonu Ekleyip o salonlara konuşmacı atamak

	@Override
	public Boolean insertEtkinlikKonusma(List<Etkinlik> lstEtkinlik, Konusmaci konusmaci) {
		// TODO Auto-generated method stub
		for (Etkinlik etkinlik : lstEtkinlik) {
			try {
				save(etkinlik, konusmaci);
			} catch (Exception ex) {
				return false;
			}
		}
		return true;
	}

	private boolean save(Etkinlik etkinlik, Konusmaci konusmaci) throws Exception {
		EtkinlikKonusmaciRel etkinlikKonusmaci = new EtkinlikKonusmaciRel();
		try {
			etkinlikKonusmaci.setEtkinlik(etkinlik);
			etkinlikKonusmaci.setKonusmaci(konusmaci);
			this.persist(etkinlikKonusmaci);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("error:102: etkinlik konusmaci ilişki " + "tablosunda hata meydana geldi.  " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean insertEtkinlikKonusma(Etkinlik etkinlik, List<Konusmaci> lstKonusmaci) {
		// TODO Auto-generated method stub
		for (Konusmaci konusmaci : lstKonusmaci) {
			try {
				save(etkinlik, konusmaci);
			} catch (Exception ex) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Konusmaci> getEtkinliktekiKonusmacilar(Long etkinlikID) {
		// TODO Auto-generated method stub
		Criteria criteria = this.createEntityCriteria();
		criteria.createAlias("etkinlik", "etk");
		criteria.add(Restrictions.eq("etk.id", etkinlikID));
		criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
		criteria.addOrder(Order.asc("createdDate"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Etkinlik> getKonusmaciEtkinlik(Long konusmaciID) {
		// TODO Auto-generated method stub
		Criteria criteria = this.createEntityCriteria();
		criteria.createAlias("konusmaci", "kns");
		criteria.add(Restrictions.eq("kns.id", konusmaciID));
		criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
		criteria.addOrder(Order.asc("createdDate"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Konusmaci> getGunlukEtkinlikKullaniciList() {
		// TODO Auto-generated method stub
		Calendar simdikiTarih = Calendar.getInstance();
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -24);
		
		String hql="select konusmaci from EtkinlikKonusmaciRel as etkinlikKonusmaci "
				+ " inner join etkinlikKonusmaci.konusmaci as konusmaci "
				+ " inner join etkinlikKonusmaci.etkinlik as etkinlik "
				+ " where etkinlik.etkinlikTarihi<=:simdikiTarih "
				+ " and etkinlik.etkinlikTarihi>=:etkinlikTarihi ";
	
		Query query=this.getSession().createQuery(hql);
		query.setCalendarDate("etkinlikTarihi", cal);
		query.setCalendar("simdikiTarih", simdikiTarih);
		query.setResultTransformer(Transformers.aliasToBean(Konusmaci.class));
		List<Konusmaci> lstKonusmaci=query.list();
		return lstKonusmaci;
	}

}
