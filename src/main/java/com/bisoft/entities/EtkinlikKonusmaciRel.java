package com.bisoft.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ETKINLIK_KONUSMACI_REL")
public class EtkinlikKonusmaciRel extends BaseEntity {

	// Many to many yapmamak için bu cozumu buldum

	// Eklenecek bir şey olursa burdan bakılacak
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "KONUSMACI_ID")
	private Konusmaci konusmaci;

	@ManyToOne
	@JoinColumn(name = "ETKINLIK")
	private Etkinlik etkinlik;

	public Konusmaci getKonusmaci() {
		return konusmaci;
	}

	public void setKonusmaci(Konusmaci konusmaci) {
		this.konusmaci = konusmaci;
	}

	public Etkinlik getEtkinlik() {
		return etkinlik;
	}

	public void setEtkinlik(Etkinlik etkinlik) {
		this.etkinlik = etkinlik;
	}
}
