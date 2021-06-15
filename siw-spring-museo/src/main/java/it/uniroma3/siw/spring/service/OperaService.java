package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operaRep;

	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRep.save(opera);
	}
	
	@Transactional
	public List<Opera> operaDaTitolo(String titolo){
		return operaRep.findByTitolo(titolo);
	}
	
	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRep.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else return null;
	}
	
	@Transactional
	public List<Opera> operePerArtista(Artista artista){
		return (List<Opera>) operaRep.findByArtista(artista);
	}

	@Transactional
	public List<Opera> operePerCollezione(Collezione collezione) {
		return (List<Opera>) operaRep.findByCollezione(collezione);
	}
	
	@Transactional
	public List<Opera> tutti(){
		return (List<Opera>) operaRep.findAll();
	}
}
