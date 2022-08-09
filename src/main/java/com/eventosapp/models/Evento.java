package com.eventosapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

// anotar a classe Entity quer dizer q a classe é uam entidade JPA estabelecera a ligacao entre entidade e uma tabela no banco de dados
@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	//toda entidade tem que ter um ID
	//generateValue pra gerar ID automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String local;
	@NotEmpty
	private String data;
	@NotEmpty
	private String horario;

	// vamos criar agora um Convidade com variavel convidados
	// List <> pq cada evento tem uma lista de convidados
	//@manyToOne = muitos convidados
	// comando abaixo para n dar erro na hora de remover evento
	@OneToMany( mappedBy = "evento",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Convidado> Convidados;


	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}




}
