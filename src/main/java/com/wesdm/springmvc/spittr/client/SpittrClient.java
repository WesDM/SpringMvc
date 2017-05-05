package com.wesdm.springmvc.spittr.client;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.wesdm.springmvc.spittr.DuplicateSpittleException;
import com.wesdm.springmvc.spittr.Spitter;
import com.wesdm.springmvc.spittr.Spittle;

public class SpittrClient {

	public Profile fetchFacebookProfile(String id) {
		RestTemplate rest = new RestTemplate();
		return rest.getForObject("http://graph.facebook.com/{spitter}", Profile.class, id);
	}

	public Spittle fetchSpittle(long id) throws DuplicateSpittleException {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Spittle> response = rest.getForEntity("http://localhost:8080/spittr-api/spittles/{id}", Spittle.class, id);
		if (response.getStatusCode() == HttpStatus.CONFLICT) {
			throw new DuplicateSpittleException();
		}
		return response.getBody();
	}

	public void updateSpittle(Spittle spittle) throws SpitterException {
		RestTemplate rest = new RestTemplate();
		String url = "http://localhost:8080/spittr-api/spittles/" + spittle.getId();
		rest.put(URI.create(url), spittle);
	}

	public void deleteSpittle(long id) {
		RestTemplate rest = new RestTemplate();
		rest.delete("http://localhost:8080/spittr-api/spittles/{id}", id);
	}

	public Spitter postSpitterForObject(Spitter spitter) {
		RestTemplate rest = new RestTemplate();
		return rest.postForObject("http://localhost:8080/spittr-api/spitters", spitter, Spitter.class);
	}

	public Spitter exchange(long id) {
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Accept", "application/json");
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<Spitter> response = rest.exchange("http://localhost:8080/spittr-api/spitters/{spitter}", HttpMethod.GET, requestEntity,
				Spitter.class, id);
		Spitter spitter = response.getBody();
		return spitter;
	}
}
