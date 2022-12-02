package com.labs.start.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.labs.start.entitys.TestEntity;

@Service
public interface TestService {
	
	
	List<TestEntity> getListTest();
	
	Optional<TestEntity> getIdTest(Long idtest);
	
	TestEntity postTest(TestEntity test);
	
	Optional<TestEntity> putTest(TestEntity test);
	
	Optional<TestEntity> delTest(Long id);

	
}
