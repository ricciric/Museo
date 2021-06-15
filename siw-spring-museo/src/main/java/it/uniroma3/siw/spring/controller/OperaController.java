package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
	@Autowired 
	private OperaValidator operaValidator;

    @RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
    public String getOpera(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	return "opera.html";
    }
    
    @RequestMapping(value = "/admin/opera/{id}", method = RequestMethod.GET)
    public String getOperaForm(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	return "admin/operaForm.html";
    }
    
	@RequestMapping(value = "/opere", method = RequestMethod.GET)
	public String getOpere(Model model) {
		model.addAttribute("opere", this.operaService.tutti());
		return "opere.html";
	}
	
	@RequestMapping(value = "/admin/opere", method = RequestMethod.GET)
	public String getOpereAdmin(Model model) {
		model.addAttribute("opere", this.operaService.tutti());
		return "admin/opere.html";
	}
	
	@RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
	public String updateOpera(@ModelAttribute("opera") Opera operaUpdated, 
    									Model model, BindingResult bindingResult){
		this.operaValidator.validate(operaUpdated, bindingResult);
		if(!bindingResult.hasErrors()) {
		this.operaService.inserisci(operaUpdated);
		model.addAttribute("opere", this.operaService.tutti());
    	return "admin/opere";
		}
		return "admin/operaForm";
    }
}
    

