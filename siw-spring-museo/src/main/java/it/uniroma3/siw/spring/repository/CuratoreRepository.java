package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Curatore;

public interface CuratoreRepository extends CrudRepository<Curatore, Long> {

    public Optional<Curatore> findByMatricola(String matricola);
    
    public Optional<Curatore> findByNumero(String numero);

    public List<Curatore> findByNomeAndCognome(String nome, String cognome);
}
