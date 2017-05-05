package com.wesdm.springmvc.spittr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.wesdm.spittr.soap.GetCountryRequest;
import com.wesdm.spittr.soap.GetCountryResponse;
import com.wesdm.springmvc.spittr.db.CountryRepository;	

/**
 * Spring WS Implemenation
 * @author Wesley
 *
 */
@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://wesdm.com/spittr/soap";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}
