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

import com.bisoft.entities.Konusmaci;
import com.bisoft.service.KonusmaciService;

@RestController
public class KonusmaciController {
	@Autowired
	private KonusmaciService service;

	@GetMapping("/konusmaciList")
	public ResponseEntity<List<Konusmaci>> getKonusmaciList() {
		List<Konusmaci> lstKonusmaci = this.service.list();
		return new ResponseEntity<List<Konusmaci>>(lstKonusmaci, HttpStatus.OK);
	}

	@GetMapping(value = "/konusmaciList/{id}")
	public ResponseEntity<Konusmaci> getKonusmaciById(@PathVariable("id") Long id) {
		Konusmaci konusmaci = this.service.findById(id);

		if (konusmaci == null) {
			return new ResponseEntity<Konusmaci>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Konusmaci>(konusmaci, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/konusmaci")
	public ResponseEntity<Konusmaci> addKonusmaci(@RequestBody Konusmaci konusmaci) {
		this.service.save(konusmaci);
		return new ResponseEntity<Konusmaci>(konusmaci, HttpStatus.CREATED);
	}

	@PutMapping(value = "/konusmaci/{id}")
	public ResponseEntity<Konusmaci> updateKonusmaci(@PathVariable Long id, @RequestBody Konusmaci konusmaci) {
		this.service.update(konusmaci);
		return new ResponseEntity<Konusmaci>(konusmaci, HttpStatus.OK);
	}

	@DeleteMapping(value = "/konusmaci/{id}")
	public ResponseEntity<Boolean> deleteEtkinlik(@PathVariable("id") Long id) {
		Boolean returnType = this.service.deleteById(id);
		return new ResponseEntity<Boolean>(returnType, HttpStatus.NO_CONTENT);
	}

}
