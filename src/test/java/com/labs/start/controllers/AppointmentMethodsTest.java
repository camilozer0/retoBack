package com.labs.start.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.labs.start.entitys.Affiliate;
import com.labs.start.entitys.Appointment;
import com.labs.start.servimpl.AppointmentServImpl;


@ExtendWith(MockitoExtension.class)

class AppointmentMethodsTest {

	@Mock
	AppointmentServImpl MockAppSI;
	
	@InjectMocks
	AppointmentMethods appMethods;


	@BeforeEach
	void setUp() throws Exception{

	}

	//Test para el meotdo de obtener lista de afiliados
	@Test
	void GetListStatus200() {
		List<Appointment> proof = new ArrayList<Appointment>();
		proof.add(new Appointment());
		when(MockAppSI.getListApp()).thenReturn(proof);
		var response = appMethods.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void GetListStatus204() {
		when(MockAppSI.getListApp()).thenReturn(Collections.emptyList());
		var response = appMethods.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	
	
	//Test para el metodo obtener test por Id
	@Test
	void GetByIdStatus200() {
		when(MockAppSI.getIdApp(anyLong())).thenReturn(Optional.of(new Appointment()));
		var response = appMethods.getById(anyLong());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	void GetByIdStatus404() {
		when(MockAppSI.getIdApp(anyLong())).thenReturn(Optional.empty());
		var response = appMethods.getById(anyLong());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
	}
	
	
	//Test para el metodo post
	@Test
	void PostStatus201() {
		var appEnt = new Appointment();
		when(MockAppSI.postApp(appEnt)).thenReturn(appEnt);
		var response = appMethods.post(appEnt);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void PostStatus404() {
		var appEnt = new Appointment();
		when(MockAppSI.postApp(appEnt)).thenReturn(null);
		var response = appMethods.post(appEnt);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}


	
	
	//Test para el metodo put
	@Test
	void PutStatus201() {
		var appEnt = new Appointment();
		when(MockAppSI.putApp(appEnt)).thenReturn(Optional.of(appEnt));
		var response = appMethods.put(appEnt);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());	
		}
	
	@Test
	void PutStatus404() {
		var appEnt = new Appointment();
		when(MockAppSI.putApp(appEnt)).thenReturn(Optional.empty());
		var response = appMethods.put(appEnt);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	
	
	//Test para el metodo delete
	@Test
	void DeleteStatus200() {
		var appdMock = anyLong();
		when(MockAppSI.delApp(appdMock)).thenReturn(Optional.of(new Appointment()));
		var response = appMethods.delete(appdMock);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
		}
	
	@Test
	void DeleteStatus204() {
		var appdMock = anyLong();
		when(MockAppSI.delApp(appdMock)).thenReturn(Optional.empty());
		var response = appMethods.delete(appdMock);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());	
		}
	
	
	@Test 
	void getByDateStatus200() {
		var appdMock = anyString();
		List<Appointment> proof = new ArrayList<Appointment>();
		proof.add(new Appointment());
		when(MockAppSI.getDateApp(appdMock)).thenReturn(proof);
		var response = appMethods.getByDate(appdMock);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}

	@Test 
	void getByDateStatus204() {
		var appdMock = anyString();
		when(MockAppSI.getDateApp(appdMock)).thenReturn(Collections.emptyList());
		var response = appMethods.getByDate(appdMock);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());	
	}
	
	
	
	
	@Test 
	void getbyAffiliatteStatus200() {
		List<Appointment> proof = new ArrayList<Appointment>();
		proof.add(new Appointment());
		var affEnt = new Affiliate();
		when(MockAppSI.getByAffiliate(affEnt)).thenReturn(proof);
		var response = appMethods.getByAffiliate(affEnt);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test 
	void getbyAffiliatteStatus204() {
		var affEnt = new Affiliate();
		when(MockAppSI.getByAffiliate(affEnt)).thenReturn(Collections.emptyList());
		var response = appMethods.getByAffiliate(affEnt);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
