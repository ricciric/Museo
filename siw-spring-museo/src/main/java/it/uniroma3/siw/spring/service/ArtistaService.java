package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository artistaRep;
	
	@Transactional
	public Artista inserisci(Artista artista) {
		return artistaRep.save(artista);
	}
	
	@Transactional
	public List<Artista> tutti() {
		return (List<Artista>) artistaRep.findAll();
	}
	
	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> optional = artistaRep.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

}
