package com.labs.start.servimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labs.start.dao.AffiliateDAO;
import com.labs.start.entitys.Affiliate;
import com.labs.start.services.AffiliateService;

@Service
public class AffiliateServImpl implements AffiliateService {

	@Autowired
	AffiliateDAO affDAO;
	
	@Override
	public List<Affiliate> getListAff() {
		return affDAO.findAll();
	}

	@Override
	public Optional<Affiliate> getIdAff(Long id) {
		return affDAO.findById(id);
	}

	@Override
	public Affiliate postAff(Affiliate affiliate) {
		return affDAO.save(affiliate);
	}

	@Override
	public Optional<Affiliate> putAff(Affiliate affiliate) {
		Optional<Affiliate> check = affDAO.findById(affiliate.getId());
		if (check.isPresent()) {
			Affiliate updateAff = check.get();
			updateAff.setName(affiliate.getName());
			updateAff.setAge(affiliate.getAge());
			updateAff.setEmail(affiliate.getEmail());
			affDAO.save(updateAff);
			return Optional.ofNullable(affiliate);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Affiliate> delAff(Long id) {
		Optional<Affiliate> check = affDAO.findById(id);
		if (check.isPresent()) {
			affDAO.deleteById(id);
			return check;
		}  else {
			return Optional.empty();
		}

	}

}
