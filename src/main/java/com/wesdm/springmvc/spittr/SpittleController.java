package com.wesdm.springmvc.spittr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/spittles")  //handler mapping
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
	@RequestMapping(method = RequestMethod.GET)  //handler mapping
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

	@RequestMapping(value = "/show", method = RequestMethod.GET)
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
	//@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)   //same as below	
	@GetMapping("/{spittleId}")
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
}
