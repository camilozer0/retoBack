package com.labs.start.servimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labs.start.dao.AffiliateDAO;
import com.labs.start.dao.AppointmentsDAO;
import com.labs.start.dao.TestsDAO;
import com.labs.start.entitys.Affiliate;
import com.labs.start.entitys.Appointment;
import com.labs.start.entitys.TestEntity;
import com.labs.start.services.AppointmentService;

@Service
public class AppointmentServImpl implements AppointmentService {

	
	@Autowired
	AppointmentsDAO appDAO;
	
	@Autowired
	AffiliateDAO affDAO;
	
	@Autowired
	TestsDAO testDAO;
	
	
	@Override
	public List<Appointment> getListApp() {
		return appDAO.findAll();
	}

	@Override
	public Optional<Appointment> getIdApp(Long id) {
		return appDAO.findById(id);
	}

	@Override
	public Appointment postApp(Appointment appointment) {
		//Busca los datos de affiliate y de test
		Optional<Affiliate> affApp = affDAO.findById(appointment.getIdAffiliate().getId());
		Optional<TestEntity> testApp = testDAO.findById(appointment.getIdTest().getId());
		if(affApp.isPresent() && testApp.isPresent()) {
			appointment.setIdAffiliate(affApp.get());
			appointment.setIdTest(testApp.get());
			return appDAO.save(appointment);
		} 
			return null;
	}

	@Override
	public Optional<Appointment> putApp(Appointment appointment) {
		Optional<Affiliate> affApp = affDAO.findById(appointment.getIdAffiliate().getId());
		Optional<TestEntity> testApp = testDAO.findById(appointment.getIdTest().getId());
		Optional<Appointment> appApp = appDAO.findById(appointment.getId());
		if(affApp.isPresent() && testApp.isPresent() && appApp.isPresent()) {
			Appointment updateApp = appointment;
			updateApp.setIdAffiliate(affApp.get());
			updateApp.setIdTest(testApp.get());
			updateApp.setDate(appointment.getDate());
			updateApp.setHour(appointment.getHour());
			appDAO.save(updateApp);
			return Optional.of(appointment);
		} else {
			return Optional.ofNullable(null);
		}
	}

	
	@Override
	public Optional<Appointment> delApp(Long id) {
		Optional<Appointment> check = appDAO.findById(id);
		if (check.isPresent()) {
			appDAO.deleteById(id);
			return check;
		}  else {
			return Optional.empty();
		}
	}

	@Override
	public List<Appointment> getDateApp(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return appDAO.findByDateOrderByIdAffiliateAsc(localDate);
	}

	@Override
	public List<Appointment> getByAffiliate(Affiliate id) {
		return appDAO.findByIdAffiliateOrderByDateAsc(id);
	}

}
