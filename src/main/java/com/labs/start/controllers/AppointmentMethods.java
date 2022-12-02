package com.labs.start.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.labs.start.entitys.Affiliate;
import com.labs.start.entitys.Appointment;
import com.labs.start.services.AppointmentService;

@RestController
@RequestMapping("Appointment")
public class AppointmentMethods {

	@Autowired
	private AppointmentService appServImp;
	

	// El metodo para consultar toda la lista de Appointments
	@GetMapping
	public ResponseEntity<List<Appointment>> getList() {
		List<Appointment> appointments = appServImp.getListApp();
		return appointments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(appointments);
	}

	// El metodo para consultar Appointment por su Id
	@RequestMapping(value = "{appointmentId}", method = RequestMethod.GET)
	public ResponseEntity<Appointment> getById(@PathVariable("appointmentId") Long appointmentId) {
		Optional<Appointment> optionalAppointment = appServImp.getIdApp(appointmentId);
		if (optionalAppointment.isPresent()) {
			return ResponseEntity.ok(optionalAppointment.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

	// El metodo para almacenar un nuevo Appointment
	@PostMapping
	public ResponseEntity<Appointment> post(@RequestBody Appointment appointment) {
			Appointment newAppointment = appServImp.postApp(appointment);
			return newAppointment != null ? ResponseEntity.status(HttpStatus.CREATED).body(newAppointment) : ResponseEntity.notFound().build();		
	}

	// el metodo para actualizar un Appointment
	@PutMapping
	public ResponseEntity<Appointment> put(@RequestBody Appointment appointment) {
		Optional<Appointment> newAppointment = appServImp.putApp(appointment);
		if(newAppointment.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// El metodo para borrar un Appointment
	@DeleteMapping(value = "{appointmentId}")
	public ResponseEntity<Void> delete(@PathVariable("appointmentId") Long appointmentId) {
			Optional<Appointment> result = appServImp.delApp(appointmentId);
			return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(null);
	}

	
	//Obtiene los appointments de una fecha y los organiza por Affiliate
	@RequestMapping(value = "listgroup/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getByDate(@PathVariable("date") String date) {
		List<Appointment> appointments = appServImp.getDateApp(date);
		if (appointments.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(appointments);
		}
	}
	
	
	// Obtiene los Appointments de un Affiliate y los organiza por fecha de forma ascendente
	@RequestMapping(value = "/Affiliate/{idAffiliate}", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getByAffiliate(@PathVariable("idAffiliate") Affiliate idAffiliate) {
		List<Appointment> appointments = appServImp.getByAffiliate(idAffiliate);
	return appointments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(appointments);
			}
	
	
	
	
}
