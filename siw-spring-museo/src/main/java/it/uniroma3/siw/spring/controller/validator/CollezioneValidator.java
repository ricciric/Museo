package it.uniroma3.siw.spring.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;

@Component
public class CollezioneValidator implements Validator{

	final Integer MAX_DESCRIZIONE_SIZE=255;

	@Override
	public void validate(Object o, Errors errors) {
		Collezione collezione = (Collezione)o;
		String nome = collezione.getNome().trim();
		String descrizione = collezione.getDescrizione();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");		
		if(descrizione.isEmpty()) 
			errors.rejectValue("descrizione", "required");
		else if(descrizione.length() > MAX_DESCRIZIONE_SIZE)
			errors.rejectValue("descrizione", "size");
		
	}
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Collezione.class.equals(clazz);
    }
}
