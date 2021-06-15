package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Utente;
import it.uniroma3.siw.spring.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	protected UtenteRepository utenteRepository;
	
	@Transactional
	public Utente getUtente(Long id) {
		Optional<Utente> u = this.utenteRepository.findById(id);
		if(u.isPresent()) 
			return u.get();
		else 
			return null;
	}
	
	@Transactional
	public Utente inserisci(Utente utente) {
		return this.utenteRepository.save(utente);
	}

	@Transactional
	public List<Utente> getAllUtenti(){
		return (List<Utente>) this.utenteRepository.findAll();
	}
}
