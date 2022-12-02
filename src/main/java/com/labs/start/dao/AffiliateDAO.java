package com.labs.start.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labs.start.entitys.Affiliate;

@Repository
public interface AffiliateDAO extends JpaRepository<Affiliate, Long>{

}
