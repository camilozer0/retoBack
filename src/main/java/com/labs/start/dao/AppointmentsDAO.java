package com.labs.start.dao;

import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

//import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labs.start.entitys.Affiliate;
import com.labs.start.entitys.Appointment;

@Repository
public interface AppointmentsDAO extends JpaRepository<Appointment, Long>{

	//Metodo para obtener citas por afiliado ordenadas por date
	List<Appointment> findByIdAffiliateOrderByDateAsc(Affiliate affiliate);
	
	//La que obtiene las citas de cierta fecha y las organiza por afiliado
	List<Appointment> findByDateOrderByIdAffiliateAsc(LocalDate date);

	
}
