package com.labs.start.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.labs.start.entitys.Affiliate;

@Service
public interface AffiliateService {

	
	List<Affiliate> getListAff();
	
	Optional<Affiliate> getIdAff(Long id);
	
	Affiliate postAff(Affiliate affiliate);
	
	Optional<Affiliate> putAff(Affiliate affiliate);
	
	Optional<Affiliate> delAff(Long id);

}
