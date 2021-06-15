package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CredenzialiValidator;
import it.uniroma3.siw.spring.controller.validator.UtenteValidator;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.model.Utente;
import it.uniroma3.siw.spring.service.CredenzialiService;

@Controller
public class AuthenticationController {

	@Autowired
	private CredenzialiService credenzialiService;

	@Autowired
	private UtenteValidator utenteValidator;

	@Autowired
	private CredenzialiValidator credenzialiValidator;

	@RequestMapping(value ="/registrazione", method = RequestMethod.GET)
	public String  mostraFormRegistrazione(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "registrazione.html";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String mostraFormLogin(Model model) {
		return "loginForm.html";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "/";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultDopoLogin(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
		if (credenziali.getRuolo().equals(Credenziali.RUOLO_ADMIN)) {
			return "admin/home.html";
		}
		return "home";
	}

	@RequestMapping(value = {"/registrazione"}, method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("utente") Utente utente,
			BindingResult utenteBindingResult,
			@ModelAttribute("credenziali") Credenziali credenziali,
			BindingResult credenzialiBindingResult,
			Model model) {
		
		// validate user and credentials fields
		this.utenteValidator.validate(utente, utenteBindingResult);
		this.credenzialiValidator.validate(credenziali, credenzialiBindingResult);

		// if neither of them had invalid contents, store the User and the Credentials into the DB
		if(!utenteBindingResult.hasErrors() && ! credenzialiBindingResult.hasErrors()) {
			credenziali.setUtente(utente);
			credenzialiService.inserisciCredenziali(credenziali);
			return "registrazioneSuccesso.html";
		}
		return "registrazione";
	}
}