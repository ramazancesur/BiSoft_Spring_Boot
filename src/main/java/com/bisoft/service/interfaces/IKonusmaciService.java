package com.bisoft.service.interfaces;

import com.bisoft.entities.Konusmaci;

public interface IKonusmaciService extends BaseService<Konusmaci> {
	Boolean sendMail(String mail);

}
