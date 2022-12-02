package com.labs.start.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.labs.start.entitys.Affiliate;
import com.labs.start.services.AffiliateService;

@RestController
@RequestMapping("Affiliate")
public class AffiliateMethods {

	@Autowired
	private AffiliateService afServImp;
	
	
	//El metodo para consultar toda la lista
	@GetMapping
	public ResponseEntity<List<Affiliate>> getList() {
		List<Affiliate> affiliates = afServImp.getListAff();
		return affiliates.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(affiliates);
	}
	
	
	//el metodo para consultar un id de la lista
	@RequestMapping(value = "{appointmentId}", method = RequestMethod.GET)
	public ResponseEntity<Affiliate> getById(@PathVariable("appointmentId") Long appointmentId) {
		Optional<Affiliate> optionalAffiliate = afServImp.getIdAff(appointmentId);
		return optionalAffiliate.isPresent() ? ResponseEntity.ok(optionalAffiliate.get()) : ResponseEntity.notFound().build();
	}
	
	
	//El metodo para almacenar un nuevo registro
	@PostMapping
	public ResponseEntity<Affiliate> post(@RequestBody Affiliate affiliate) {
			Affiliate newAffiliate = afServImp.postAff(affiliate);
		return newAffiliate != null ? ResponseEntity.status(HttpStatus.CREATED).body(newAffiliate) : ResponseEntity.notFound().build();
	}
	
	
	//el metodo para actualizar un registro
	@PutMapping
	public ResponseEntity<Affiliate> put(@RequestBody Affiliate affiliate) {
		Optional<Affiliate> optionalAffiliate = afServImp.putAff(affiliate);
		if (optionalAffiliate.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(optionalAffiliate.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}	
	}
	
	
	//Metodo para borrar un registro
	@DeleteMapping(value = "{affiliateId}")
	public ResponseEntity<Void> delete(@PathVariable("affiliateId") Long affiliateId) {
			Optional<Affiliate> result = afServImp.delAff(affiliateId);
			return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(null);
	}
	
}
