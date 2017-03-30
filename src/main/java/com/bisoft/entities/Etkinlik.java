package com.bisoft.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ETKINLIK")
public class Etkinlik extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String adi;
	@Column
	private String yer;
	@Column(name = "ICERIK")
	private String içerik;
	@Column(name = "SAAT")
	private String saat;
	@Column(name = "SURE")
	private Integer sure;
	@Column(name="ETKINLIK_TARIHI")
	private Date etkinlikTarihi;

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getYer() {
		return yer;
	}

	public void setYer(String yer) {
		this.yer = yer;
	}

	public String getIçerik() {
		return içerik;
	}

	public void setIçerik(String içerik) {
		this.içerik = içerik;
	}

	public String getSaat() {
		return saat;
	}

	public void setSaat(String saat) {
		this.saat = saat;
	}

	public Integer getSure() {
		return sure;
	}

	public void setSure(Integer sure) {
		this.sure = sure;
	}

	public Date getEtkinlikTarihi() {
		return etkinlikTarihi;
	}

	public void setEtkinlikTarihi(Date etkinlikTarihi) {
		this.etkinlikTarihi = etkinlikTarihi;
	}
	

}
