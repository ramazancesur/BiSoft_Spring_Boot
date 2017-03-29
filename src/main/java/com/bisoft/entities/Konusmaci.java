package com.bisoft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "KONUSMACI")
public class Konusmaci extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "MAIL_ADRES", unique = true, nullable = false)
	private String mailAdres;
	@Column(name = "ADI", length = 50)
	private String adi;
	@Column(name = "SOYADI", length = 50)
	private String soyadi;
	@Column(name = "TELEFON", length = 11)
	private String telefon;
	@Column(name = "ADRES")
	private String adres;

	public String getMailAdres() {
		return mailAdres;
	}

	public void setMailAdres(String mailAdres) {
		this.mailAdres = mailAdres;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getSoyadi() {
		return soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
}
