package com.bisoft.service.interfaces;

import com.bisoft.entities.Etkinlik;

public interface IEtkinlikService extends BaseService<Etkinlik> {
	Boolean hatirlaticiMailGonder(String email);
}