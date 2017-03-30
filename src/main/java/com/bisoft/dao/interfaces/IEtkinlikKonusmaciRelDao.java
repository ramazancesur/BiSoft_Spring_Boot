package com.bisoft.dao.interfaces;

import java.util.List;

import com.bisoft.entities.Etkinlik;
import com.bisoft.entities.EtkinlikKonusmaciRel;
import com.bisoft.entities.Konusmaci;

public interface IEtkinlikKonusmaciRelDao extends GenericDao<Long, EtkinlikKonusmaciRel> {
	Boolean insertEtkinlikKonusma(List<Etkinlik> lstEtkinlik, Konusmaci konusmaci);

	Boolean insertEtkinlikKonusma(Etkinlik etkinlik, List<Konusmaci> lstKonusmaci);

	List<Konusmaci> getEtkinliktekiKonusmacilar(Long etkinlikID);

	List<Etkinlik> getKonusmaciEtkinlik(Long konusmaciID);

	List<Konusmaci> getGunlukEtkinlikKullaniciList();
	/*
	 * Eger istenirse bir tane hashMapden parametre olarak filitreleme yapılacak
	 * alanlar alınabilir de fazla abartmak istemedim ,
	 * 
	 * Refrectionlarla da alan ismi kontrollerini unutmayalım
	 */
}