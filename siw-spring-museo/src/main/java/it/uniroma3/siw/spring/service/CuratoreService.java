package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CuratoreService {

    @Autowired
    private CuratoreRepository curatoreRepository;


    @Transactional
    public Curatore inserisci(Curatore curatore) {
        return curatoreRepository.save(curatore);
    }

    @Transactional 
    public Curatore curatoriPerMatricola(String matricola) {
    	Optional<Curatore> optional = curatoreRepository.findByMatricola(matricola);
        if(optional.isPresent()) {
            return optional.get();
        }
        else {
            return null;
        }
    }
    
    @Transactional 
    public Curatore curatoriPerNumero(String numero) {
    	Optional<Curatore> optional = curatoreRepository.findByMatricola(numero);
        if(optional.isPresent()) {
            return optional.get();
        }
        else {
            return null;
        }
    }

    @Transactional
    public List<Curatore> curatorePerNomeECognome(String nome, String cognome) {
        return curatoreRepository.findByNomeAndCognome(nome, cognome);
    }

    @Transactional
    public List<Curatore> tuttiICuratori() {
        return (List<Curatore>) curatoreRepository.findAll();
    }

    @Transactional 
    public Curatore curatorePerId(Long id) {
        Optional<Curatore> optional = curatoreRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        else {
            return null;
        }
    }

    @Transactional
    public boolean alreadyExists(Curatore curatore) {
        List<Curatore> curatori = curatoreRepository.findByNomeAndCognome(curatore.getNome(), curatore.getCognome());
        if(curatori.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
