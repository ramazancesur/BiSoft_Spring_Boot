package com.bisoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bisoft.entities.Etkinlik;
import com.bisoft.entities.Konusmaci;
import com.bisoft.service.interfaces.IEtkinlikService;
import com.bisoft.service.interfaces.IKonusmaciService;

@RestController
public class EtkinlikController {
	@Autowired
	private IEtkinlikService service;

	@Autowired
	private IKonusmaciService konusmaciService;

	@GetMapping("/etkinlikList")
	public ResponseEntity<List<Etkinlik>> getEtkiniks() {
		List<Etkinlik> lstEtkinlik = this.service.list();
		return new ResponseEntity<List<Etkinlik>>(lstEtkinlik, HttpStatus.OK);
	}

	@GetMapping("/etkinlik/{KonusmaciId}")
	public ResponseEntity<Boolean> sendMail(@PathVariable("konusmaciID") Long konusmaciID) {
		Konusmaci konusmaci = konusmaciService.findById(konusmaciID);
		if (konusmaci != null) {
			String mail = konusmaci.getMailAdres();
			boolean sonuc = service.hatirlaticiMailGonder(mail);
			return new ResponseEntity<Boolean>(sonuc, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/etkinlikList/{id}")
	public ResponseEntity<Etkinlik> getEtkinlikById(@PathVariable("id") Long id) {
		Etkinlik etkinlik = this.service.findById(id);

		if (etkinlik == null) {
			return new ResponseEntity<Etkinlik>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Etkinlik>(etkinlik, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/etkinlik")
	public ResponseEntity<Etkinlik> addEtkinlik(@RequestBody Etkinlik etkinlik) {
		this.service.save(etkinlik);
		return new ResponseEntity<Etkinlik>(etkinlik, HttpStatus.CREATED);
	}

	@PutMapping(value = "/etkinlik/{id}")
	public ResponseEntity<Etkinlik> updateEtkinlik(@PathVariable Long id, @RequestBody Etkinlik etkinlik) {
		this.service.update(etkinlik);
		return new ResponseEntity<Etkinlik>(etkinlik, HttpStatus.OK);
	}

	@DeleteMapping(value = "/etkinlik/{id}")
	public ResponseEntity<Boolean> deleteEtkinlik(@PathVariable("id") Long id) {
		Boolean returnType = this.service.deleteById(id);
		return new ResponseEntity<Boolean>(returnType, HttpStatus.NO_CONTENT);
	}
}
