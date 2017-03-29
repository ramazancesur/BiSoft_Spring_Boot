package com.bisoft.service.interfaces;

import java.util.List;

import com.bisoft.entities.Etkinlik;
import com.bisoft.entities.Konusmaci;

public interface IEtkinlikService extends BaseService<Etkinlik> {
	Boolean hatirlaticiMailGonder(String email);

	Boolean insertEtkinlikKonusma(List<Etkinlik> lstEtkinlik, Konusmaci konusmaci);

	Boolean insertEtkinlikKonusma(Etkinlik etkinlik, List<Konusmaci> lstKonusmaci);

	List<Konusmaci> getEtkinliktekiKonusmacilar(Long etkinlikID);

	List<Etkinlik> getKonusmaciEtkinlik(Long konusmaciID);
}