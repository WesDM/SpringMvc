package com.wesdm.springmvc.spittr;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter()); // command name set to spitter on
											// register jsp, have to add new
											// spitter object to model
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@RequestPart("profilePicture") byte[] profilePicture, @Valid Spitter spitter,
			Errors errors, RedirectAttributes model) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spitterRepository.save(spitter);
		// carry data across redirects via url template. would look like this
		// /spitter/habuma?spitterId=42
		model.addAttribute("username", spitter.getUsername());
		// model.addAttribute("spitterId", spitter.getId());

		// redirect using flash attribute to send object Flash attributes are
		// stored
		// in the session and then retrieved into
		// the model, surviving a redirect
		model.addFlashAttribute("spitter", spitter);
		// When handling a POST request, it’s usually a good idea to send a
		// redirect after the
		// POST has completed processing so that a browser refresh won’t
		// accidentally submit the
		// form a second time.
		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if (!model.containsAttribute("spitter")) {
			Spitter spitter = spitterRepository.findByUsername(username);
			model.addAttribute(spitter);
		}
		return "profile";
	}
}
