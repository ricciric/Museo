package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CuratoreService;

@Component
public class CuratoreValidator implements Validator{

	@Autowired
	private CuratoreService curatoreService;
	
	@Override
    public void validate(Object o, Errors errors) {
		Curatore curatore = (Curatore)o;
		String nome = curatore.getNome().trim();
		String cognome = curatore.getCognome().trim();
		String luogo = curatore.getLuogoNascita().trim();
		String email = curatore.getEmail().trim();
		String numero = curatore.getNumero().trim();
		String matricola = curatore.getMatricola().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		if(cognome.isEmpty())
			errors.rejectValue("cognome", "required");
		if(luogo.isEmpty())
			errors.rejectValue("luogo", "required");
		if(email.isEmpty())
			errors.rejectValue("email", "required");
		else if(!(email.contains("@")))
			errors.rejectValue("email", "missing");
		if(numero.isEmpty())
			errors.rejectValue("numero", "required");
		else if(numero.contains("[a-Z]"))
			errors.rejectValue("numero", "characters");
		else if(this.curatoreService.curatoriPerNumero(numero) != null)
			errors.rejectValue("numero", "duplicate");
		if(matricola.isEmpty())
			errors.rejectValue("matricola", "required");      
		else if(matricola.contains("[a-Z]"))
			errors.rejectValue("matricola", "characters");
	}
	
	@Override
    public boolean supports(Class<?> clazz) {
        return Curatore.class.equals(clazz);
    }

}
