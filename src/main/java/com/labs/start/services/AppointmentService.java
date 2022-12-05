package com.labs.start.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.labs.start.entitys.Affiliate;
import com.labs.start.entitys.Appointment;

@Service
public interface AppointmentService {

	
	List<Appointment> getListApp();
	
	Optional<Appointment> getIdApp(Long id);
	
	Appointment postApp(Appointment appointment);
	
	Optional<Appointment> putApp(Appointment appointment);
	
	Optional<Appointment> delApp(Long id);
	
	List<Appointment> getDateApp(String date);
	
	List<Appointment> getByAffiliate(Affiliate id);
}
