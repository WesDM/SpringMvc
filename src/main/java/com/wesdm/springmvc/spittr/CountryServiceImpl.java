package com.wesdm.springmvc.spittr;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.wesdm.spittr.soap.Country;
import com.wesdm.springmvc.spittr.db.CountryRepository;

@WebService(endpointInterface = "com.wesdm.springmvc.spittr.CountryService")
public class CountryServiceImpl implements CountryService{
	
	private CountryRepository countryRepository;

	@Autowired
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public Country getCountry(String name) {
		return countryRepository.findCountry(name);
	}

}
