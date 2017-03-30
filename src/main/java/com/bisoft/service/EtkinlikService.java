package com.bisoft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bisoft.dao.interfaces.IEtkinlikDao;
import com.bisoft.dao.interfaces.IEtkinlikKonusmaciRelDao;
import com.bisoft.entities.Etkinlik;
import com.bisoft.entities.Konusmaci;
import com.bisoft.helper.Helper;
import com.bisoft.service.interfaces.IEtkinlikService;

@Transactional
@Service("ETKINLIK_SERVICE")
public class EtkinlikService implements IEtkinlikService {

	@Autowired
	private IEtkinlikDao etkinlikDao;

	@Autowired
	private IEtkinlikKonusmaciRelDao etkinlikKullaniciDao;

	@Autowired
	private JavaMailSender javaMailService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EtkinlikService.class);

	Helper helper = Helper.getInstance();

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
		if (helper.isValidEmailAddress(email) != false) {
			try {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(email);
				mailMessage.setFrom("ramazancesur3");
				mailMessage.setSubject("Hatirlatma");
				mailMessage.setText("Bu bir hatırlatma mailidir 24 saat içinde bir tane etkinliğiniz vardır");
				javaMailService.send(mailMessage);
				return true;
			} catch (Exception ex) {
				LOGGER.error("mail atarken hata oluştu " + email + " " + ex.getMessage());
				return false;
			}
		}
		else{
			LOGGER.warn("Mail servisinde hatalı mail "+email );
			return false;
		}
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
