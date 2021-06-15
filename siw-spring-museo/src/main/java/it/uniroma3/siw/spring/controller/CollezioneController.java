package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;

	@Autowired CollezioneValidator collezioneValidator;



	@RequestMapping(value = "/collezioni", method = RequestMethod.GET)
	public String getCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutti());
		return "collezioni.html";
	}

	@RequestMapping(value ="/collezione/{id}", method = RequestMethod.GET)
	public String getCollezione(@PathVariable("id") Long id, Model model) {
		Collezione collezione = this.collezioneService.collezionePerId(id);
		model.addAttribute("collezione", collezione);
		model.addAttribute("opere", collezione.getOpere());
		return "collezione.html";
	}

	@RequestMapping(value = "/admin/collezione/{id}", method = RequestMethod.GET)
	public String getCollezioneForm(@PathVariable("id") Long id, Model model) {
		Collezione collezione = this.collezioneService.collezionePerId(id);
		model.addAttribute("collezione", collezione);
		return "admin/collezioneForm.html";
	}

	@RequestMapping(value = "/admin/lista_collezioni", method = RequestMethod.GET)
	public String getCollezioniAdmin(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutti());
		return "admin/lista_collezioni.html";
	}

	@RequestMapping(value = "/admin/collezioni", method = RequestMethod.POST)
	public String updateCollezione(@ModelAttribute("collezione") Collezione collezione, BindingResult collezionebindingResult, Model model){
		//validate Collezione
		this.collezioneValidator.validate(collezione, collezionebindingResult);
		if(!(collezionebindingResult.hasErrors())) {
			this.collezioneService.inserisci(collezione);
			model.addAttribute("collezioni", this.collezioneService.tutti());
			return "admin/lista_collezioni.html";
		}
		return "admin/collezioneForm.html";
	}
}
