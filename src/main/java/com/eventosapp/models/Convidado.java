package com.eventosapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotEmpty;


// anotar Entity pq tbm usabanco de dados e é uam entidade
@Entity
public class Convidado {

	// anotar pq RG é unico e o ID sera nossa chave primaria dos convidados
	// @NotEmpty significa q ele não vai ser vazio ao digitar no campo de cadastro
	@Id
	@NotEmpty
	private String rg;
	@NotEmpty
	private String nomeConvidado;

	// cada evento vai ter uma lista de convidados precisamos montar essa relação entre a entidade convidade e entidade evento
	//entidade convidado vamos criar um Evento da variavel envento pra ter relação de ManyToOne (muitos convitados para 1 só evento)
	//@manyToOne = muitos convidados
	@ManyToOne
	private Evento evento;

	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	// vamos gerar os getter e setters desse vento
		// pq ele vai receber o codigo relacionado a esse evento, cada candidado vai ter um codigo sobre o evento
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}



}
