package com.labs.start.servimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labs.start.dao.TestsDAO;
import com.labs.start.entitys.TestEntity;
import com.labs.start.services.TestService;

@Service
public class TestServImpl implements TestService {
	
	@Autowired
	TestsDAO testDAO;

	@Override
	public List<TestEntity> getListTest() {
		return testDAO.findAll();
	}

	@Override
	public Optional<TestEntity> getIdTest(Long idtest) {
		return testDAO.findById(idtest);
	}

	@Override
	public TestEntity postTest(TestEntity test) {
		return testDAO.save(test);
	}

	@Override
	public Optional<TestEntity> putTest(TestEntity test) {
		Optional<TestEntity> check = testDAO.findById(test.getId());
		if (check.isPresent()) {
			TestEntity updateTest = check.get();
			updateTest.setName(test.getName());
			updateTest.setDescription(test.getDescription());
			testDAO.save(updateTest);
			return Optional.ofNullable(updateTest);
		} else {
			return Optional.empty();
		}

	}

	@Override
	public Optional<TestEntity> delTest(Long id) {
		Optional<TestEntity> check = testDAO.findById(id);
			if (check.isPresent()) {
				testDAO.deleteById(id);
				return check;
			}
			return Optional.empty();
		}
	}

