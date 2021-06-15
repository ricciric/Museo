package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Curatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascita;
	
	private String luogoNascita;
	
	private String email;
	
	private String numero;
	
	@Column(unique = true)
	private String matricola;
	
	@OneToMany(mappedBy = "curatore")
	private List<Collezione> collezioni;

	
	public Curatore(String nome, String cognome, LocalDate dataNascita, String luogoNascita, String email, String numero, String matricola) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.email = email;
		this.numero = numero;
		this.matricola = matricola;
		this.collezioni = new ArrayList<Collezione>();
	}

	public Curatore() {
		this.collezioni = new ArrayList<Collezione>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}
	
	

}
