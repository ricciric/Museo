package it.uniroma3.siw.spring.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Opera;

@Component
public class OperaValidator implements Validator{

	final Integer MAX_DESCRIZIONE_SIZE=500;
	
	@Override
	public void validate(Object o, Errors errors) {
		Opera opera = (Opera)o;
		String titolo = opera.getTitolo().trim();
		String anno = opera.getAnno().trim();
		String descrizione = opera.getDescrizione().trim();
		
		if(titolo.isEmpty())
			errors.rejectValue("titolo", "required");
		if(anno.isEmpty())
			errors.rejectValue("anno", "required");
		if(descrizione.isEmpty()) 
			errors.rejectValue("descrizione", "required");
		else if(descrizione.length() > MAX_DESCRIZIONE_SIZE)
			errors.rejectValue("descrizione", "size");

	}
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Opera.class.equals(clazz);
    }

}
