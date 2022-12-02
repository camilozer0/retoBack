package com.labs.start.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.labs.start.entitys.TestEntity;
import com.labs.start.services.TestService;

@RestController
@RequestMapping("Test")
public class TestMethods {

	@Autowired
	private TestService tServImp;

	// El metodo para consultar toda la lista
	@GetMapping
	public ResponseEntity<List<TestEntity>> getList() {
		List<TestEntity> tests = tServImp.getListTest();
		return tests.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tests);
	}

	// El metodo para consultar un id de la lista
	@RequestMapping(value = "{testId}", method = RequestMethod.GET)
	public ResponseEntity<TestEntity> getById(@PathVariable("testId") Long testId) {
		Optional<TestEntity> optionalTest = tServImp.getIdTest(testId);
		return optionalTest.isPresent() ? ResponseEntity.ok(optionalTest.get()) : ResponseEntity.notFound().build();
	}
	

	// El metodo para almacenar un nuevo registro 
	@PostMapping
	public ResponseEntity<TestEntity> post(@RequestBody TestEntity test) {
			TestEntity newTest = tServImp.postTest(test);
			return newTest != null ? ResponseEntity.status(HttpStatus.CREATED).body(newTest) : ResponseEntity.notFound().build();		
	}

	// el metodo para actualizar un registro (revisar que este metodo recibe un json)
	@PutMapping
	public ResponseEntity<TestEntity> put(@RequestBody TestEntity test) {
		Optional<TestEntity> optionalTest = tServImp.putTest(test);
		return optionalTest.isPresent() ? ResponseEntity.status(HttpStatus.CREATED).body(optionalTest.get()) : ResponseEntity.notFound().build();
	}
	
	//El metodo para borrar un registro
	@DeleteMapping(value = "{testId}")
	public ResponseEntity<Void> delete(@PathVariable("testId") Long testId) {
		Optional<TestEntity> result =  tServImp.delTest(testId);
		return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(null);
	}
	
	
	
}
