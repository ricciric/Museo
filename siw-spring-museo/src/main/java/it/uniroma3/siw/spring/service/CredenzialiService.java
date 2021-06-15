package it.uniroma3.siw.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.repository.CredenzialiRepository;

@Service
public class CredenzialiService {
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Autowired
	private CredenzialiRepository credenzialiRepository;
	
	@Transactional
	public Credenziali getCredenziali(Long id) {
		Optional<Credenziali> c = this.credenzialiRepository.findById(id);
		if(c.isPresent())
			return c.get();
		else
			return null;
	}
	
	@Transactional
	public Credenziali getCredenziali(String username) {
		Optional<Credenziali> c = this.credenzialiRepository.findByUsername(username);
		if(c.isPresent())
			return c.get();
		else
			return null;
	}
	
	@Transactional
	public Credenziali inserisciCredenziali(Credenziali credenziali) {
		credenziali.setRuolo(Credenziali.RUOLO_DEFAULT);
		credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
		return this.credenzialiRepository.save(credenziali);
	}
	
}
