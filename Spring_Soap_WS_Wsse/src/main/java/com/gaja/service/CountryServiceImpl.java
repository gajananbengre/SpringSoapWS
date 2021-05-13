package com.gaja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaja.error.CountryNotFound;
import com.gaja.model.CountryMO;
import com.gaja.repo.CountryRepository;

@Service
public class CountryServiceImpl {

	@Autowired
	private CountryRepository repo;
	
	
	public CountryMO findCountry(String name) {
		CountryMO country = repo.findByName(name);
		if(country == null) {
			throw new CountryNotFound("COUNTRY NAME NOT FOUND");
		}
		return country;
	}
}
