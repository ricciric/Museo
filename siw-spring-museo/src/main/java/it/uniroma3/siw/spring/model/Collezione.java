package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Collezione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 

	@Column(nullable = false)
	private String nome;
	
	private String descrizione;
	
	@ManyToOne
	private Curatore curatore;
	
	@OneToMany(mappedBy = "collezione")
	private List<Opera> opere;
	
	public Collezione(String nome, String descrizione, Curatore curatore) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.curatore = curatore;
		this.opere = new ArrayList<Opera>();
	}

	public Collezione() {
		this.opere = new ArrayList<Opera>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curatore getCuratore() {
		return curatore;
	}

	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	
	

}
