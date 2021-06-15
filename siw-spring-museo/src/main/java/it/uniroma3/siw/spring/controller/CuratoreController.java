package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;

@Controller
public class CuratoreController {

	@Autowired
	private CuratoreService curatoreService;
	
	@Autowired
	private CuratoreValidator curatoreValidator;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@RequestMapping(value = "/admin/curatore/{id}", method = RequestMethod.GET)
	public String getCuratoreForm(@PathVariable("id") Long id, Model model) {
		Curatore curatore = this.curatoreService.curatorePerId(id);
		model.addAttribute("curatore", curatore);
		return "admin/curatoreForm.html";
	}
	
	@RequestMapping(value = "/admin/curatore", method = RequestMethod.POST)
	public String updateCuratore(@ModelAttribute("curatore") Curatore curatore, BindingResult curatoreBindingResult, Model model) {
		//validate curatore
		this.curatoreValidator.validate(curatore, curatoreBindingResult);
		if(!curatoreBindingResult.hasErrors()) {
			this.curatoreService.inserisci(curatore);
			model.addAttribute("collezioni", collezioneService.tutti());
			return "admin/lista_collezioni";
		}
		return "admin/curatoreForm";
	}
	

}
