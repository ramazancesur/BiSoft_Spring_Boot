package com.bisoft.quartzJob;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bisoft.dao.interfaces.IEtkinlikKonusmaciRelDao;
import com.bisoft.entities.Konusmaci;
import com.bisoft.helper.Helper;

@Component
@Transactional
public class MailService {
	@Autowired
	private JavaMailSender javaMailService;

	@Autowired
	private IEtkinlikKonusmaciRelDao etkinlikKonusmaciDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	private Helper helper = Helper.getInstance();

	private List<String> getGunlukEtkinlikKullaniciList() {
		List<Konusmaci> lstKonusmaci = etkinlikKonusmaciDao.getGunlukEtkinlikKullaniciList();
		List<String> lstMail = new LinkedList<>();
		for (Konusmaci konusmaci : lstKonusmaci) {
			if (helper.isValidEmailAddress(konusmaci.getMailAdres())) {
				lstMail.add(konusmaci.getMailAdres());
			}
		}
		return lstMail;
	}
	
	@Scheduled(cron = "0 0 * * * *")
	public void SendMail() {
		List<String> lstMail=getGunlukEtkinlikKullaniciList();
		for(String email:lstMail){
			try {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(email);
				mailMessage.setFrom("ramazancesur3");
				mailMessage.setSubject("Hatirlatma");
				mailMessage.setText("Bu bir hatırlatma mailidir 24 saat içinde bir tane etkinliğiniz vardır");
				javaMailService.send(mailMessage);
			} catch (Exception ex) {
				LOGGER.error("mail atarken hata oluştu " + email + " " + ex.getMessage());
			}	
		}
	}
}