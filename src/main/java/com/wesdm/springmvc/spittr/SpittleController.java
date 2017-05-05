package com.wesdm.springmvc.spittr;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.wesdm.springmvc.spittr.db.SpittleRepository;

@Controller
@RequestMapping("/spittles") // handler mapping
public class SpittleController {

	private SpittleRepository spittleRepository;

	private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	/**
	 * Example: /spittles/show?spittle_id=12345
	 * 
	 * @param max
	 * @param count
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET) // handler mapping
	public String spittles(@RequestParam(value = "max", defaultValue = "9223372036854775807") long max,
			@RequestParam(value = "count", defaultValue = "20") int count, Model model) {
		model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
		return "spittles";
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public String spittles(Model model) {
	// // Model is essentially a map (that is, a collection of key-value pairs)
	// // that will be
	// // handed off to the view so that the data can be rendered to the client
	// model.addAttribute("spittleList",
	// spittleRepository.findSpittles(Long.MAX_VALUE, 20));
	// return "spittles";
	// }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	/**
	 * Example: /spittles/54321
	 * 
	 * @param spittleId
	 * @param model
	 * @return
	 */
	/*
	 * Restful service. @ResponseBody converts based on HttpMessageConverter and adds data directly to response instead of looking for view. If this
	 * were in a @RestController (available Spring 4.0+) class then @ResponseBody would be applied by default
	 * 
	 * Even better is to return a ResponseEntity which carries metadata such as headers and status code.
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	// @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	// @ResponseBody - not needed when using ResponseEntity
	public ResponseEntity<Spittle> showSpittleRest(@PathVariable("spittle_id") long spittleId, Model model) {
		Spittle spittle = spittleRepository.findOne(spittleId);
		if (spittle == null) {
			throw new SpittleNotFoundException(spittleId);
		}
		return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle(SpittleForm form, Model model) throws DuplicateSpittleException {
		spittleRepository.save(new Spittle(form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
		return "redirect:/spittles";
	}
	
	/**
	 * Build URL of saved spittle using UriComponentsBuilder based on environmnet
	 * @param spittle
	 * @param ucb
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
		Spittle saved = spittleRepository.save(spittle);
		HttpHeaders headers = new HttpHeaders();
		URI locationUri = ucb.path("/spittles/").path(String.valueOf(spittle.getId())).build().toUri();
		headers.setLocation(locationUri);
		ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(saved, headers, HttpStatus.CREATED);
		return responseEntity;
	}

	@ExceptionHandler(SpittleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Error spittleNotFound(SpittleNotFoundException e) {
		long spittleId = e.getSpittleId();
		return new Error(4, "Spittle [" + spittleId + "] not found");
	}
}
