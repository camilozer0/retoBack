package com.labs.start.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labs.start.entitys.TestEntity;

@Repository
public interface TestsDAO extends JpaRepository<TestEntity, Long>{

}
