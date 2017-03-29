package com.bisoft.dao.interfaces;

import com.bisoft.entities.Etkinlik;

public interface IEtkinlikDao extends GenericDao<Long, Etkinlik> {
	boolean hatirlaticiMailGonder();
}
