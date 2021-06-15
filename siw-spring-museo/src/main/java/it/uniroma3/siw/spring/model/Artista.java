package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascita;
	
	private String luogoNascita;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataMorte;
	
	private String luogoMorte;
	
	private String nazionalita;
	
	@Column(length = 500)
	private String biografia;
	
	@OneToMany(mappedBy = "artista")
	private List<Opera> opere;
	
	public Artista() {
		this.opere = new ArrayList<Opera>();
	}
	
	public Artista(String nome, String cognome, LocalDate dataNascita, String luogoNascita, LocalDate dataMorte, String luogoMorte, String nazionalita, String biografia) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.dataMorte = dataMorte;
		this.luogoMorte = luogoMorte;
		this.nazionalita = nazionalita;
		this.biografia = biografia;
		this.opere = new ArrayList<Opera>();
	}



	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}

	public String getLuogoMorte() {
		return luogoMorte;
	}

	public void setLuogoMorte(String luogoMorte) {
		this.luogoMorte = luogoMorte;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	
	
	
}
