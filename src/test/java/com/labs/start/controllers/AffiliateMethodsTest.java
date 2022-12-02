package com.labs.start.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.labs.start.entitys.Affiliate;
import com.labs.start.servimpl.AffiliateServImpl;


@ExtendWith(MockitoExtension.class)

class AffiliateMethodsTest {

	
	@Mock
	AffiliateServImpl MockASI;
	
	@InjectMocks
	AffiliateMethods affMethods;
	
	@Captor
	private ArgumentCaptor<Optional<Affiliate>> pruebaAff;

	@BeforeEach
	void setUp() throws Exception{

	}

	//Test para el meotdo de obtener lista de afiliados
	@Test
	void GetListStatus200() {
		List<Affiliate> proof = new ArrayList<Affiliate>();
		proof.add(new Affiliate());
		when(MockASI.getListAff()).thenReturn(proof);
		var response = affMethods.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void GetListStatus204() {
		when(MockASI.getListAff()).thenReturn(Collections.emptyList());
		var response = affMethods.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	
	//Test para el metodo obtener test por Id
	@Test
	void GetByIdStatus200() {
		when(MockASI.getIdAff(anyLong())).thenReturn(Optional.of(new Affiliate()));
		var response = affMethods.getById(anyLong());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	void GetByIdStatus404() {
		when(MockASI.getIdAff(anyLong())).thenReturn(Optional.empty());
		var response = affMethods.getById(anyLong());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
	}
	
	
	//Test para el metodo post
	@Test
	void PostStatus201() {
		var affEnt = new Affiliate();
		when(MockASI.postAff(affEnt)).thenReturn(affEnt);
		var response = affMethods.post(affEnt);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void PostStatus404() {
		var affEnt = new Affiliate();
		when(MockASI.postAff(affEnt)).thenReturn(null);
		var response = affMethods.post(affEnt);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}


	
	
	//Test para el metodo put
	@Test
	void PutStatus201() {
		var affEnt = new Affiliate();
		when(MockASI.putAff(affEnt)).thenReturn(Optional.of(affEnt));
		var response = affMethods.put(affEnt);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());	
		}
	
	@Test
	void PutStatus404() {
		var affEnt = new Affiliate();
		when(MockASI.putAff(affEnt)).thenReturn(Optional.empty());
		var response = affMethods.put(affEnt);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	
	
	//Test para el metodo delete
	@Test
	void DeleteStatus200() {
		var affIdMock = anyLong();
		when(MockASI.delAff(affIdMock)).thenReturn(Optional.of(new Affiliate()));
		var response = affMethods.delete(affIdMock);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
		}
	
	@Test
	void DeleteStatus204() {
		var affIdMock = anyLong();
		when(MockASI.delAff(affIdMock)).thenReturn(Optional.empty());
		var response = affMethods.delete(affIdMock);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());	
		}

}
