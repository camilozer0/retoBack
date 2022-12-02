package com.labs.start.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import com.labs.start.entitys.TestEntity;
import com.labs.start.services.TestService;

@ExtendWith(MockitoExtension.class)

class TestMethodsTest {

	
	@Mock
	TestService MockTSI;
	
	@InjectMocks
	TestMethods testMethods;
	
	@Captor
	private ArgumentCaptor<Optional<TestEntity>> prueba;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	//Test para el metdo de obtener lista de Test
	@Test
	void GetListStatus200() {
		List<TestEntity> proof = new ArrayList<TestEntity>();
		proof.add(new TestEntity());
		when(MockTSI.getListTest()).thenReturn(proof);
		var response = testMethods.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void GetListStatus204() {
		when(MockTSI.getListTest()).thenReturn(Collections.emptyList());
		var response = testMethods.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	
	
	//Test para el metodo obtener test por Id
	@Test
	void GetByIdStatus200() {
		when(MockTSI.getIdTest(anyLong())).thenReturn(Optional.of(new TestEntity()));
		var response = testMethods.getById(anyLong());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	void GetByIdStatus404() {
		when(MockTSI.getIdTest(anyLong())).thenReturn(Optional.empty());
		var response = testMethods.getById(anyLong());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
	}
	
	
	//Test para el metodo post
	@Test
	void PostStatus201() {
		var testEnt = new TestEntity();
		when(MockTSI.postTest(testEnt)).thenReturn(testEnt);
		var response = testMethods.post(testEnt);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void PostStatus404() {
		var testEnt = new TestEntity();
		when(MockTSI.postTest(testEnt)).thenReturn(null);
		var response = testMethods.post(testEnt);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}


	
	
	//Test para el metodo put
	@Test
	void PutStatus201() {
		var testEnt = new TestEntity();
		when(MockTSI.putTest(testEnt)).thenReturn(Optional.of(testEnt));
		var response = testMethods.put(testEnt);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());	
		}
	
	@Test
	void PutStatus404() {
		var testEnt = new TestEntity();
		when(MockTSI.putTest(testEnt)).thenReturn(Optional.empty());
		var response = testMethods.put(testEnt);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	
	
	//Test para el metodo delete
	@Test
	void DeleteStatus200() {
		var testIdmock = anyLong();
		when(MockTSI.delTest(testIdmock)).thenReturn(Optional.of(new TestEntity()));
		var response = testMethods.delete(testIdmock);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	}
	
	@Test
	void DeleteStatus204() {
		var testIdmock = anyLong();
		when(MockTSI.delTest(testIdmock)).thenReturn(Optional.empty());
		var response = testMethods.delete(testIdmock);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());	}

}
