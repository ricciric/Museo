package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	

	@RequestMapping(value = "/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.tutti());
		return "artisti.html";
	}
    
    @RequestMapping(value ="/artista/{id}", method = RequestMethod.GET)
    public String getArtista(@PathVariable("id") Long id, Model model) {
    	Artista artista = this.artistaService.artistaPerId(id);
    	model.addAttribute("artista", artista);
    	model.addAttribute("opere", artista.getOpere());
    	return "artista.html";
    }
    
}
