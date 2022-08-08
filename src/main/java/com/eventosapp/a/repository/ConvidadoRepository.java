package com.eventosapp.a.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

	// estamos criando esse método para na pagina Evento mostre na tela abaixo da lista de Eventos as infoamções na tela
	// aqui ele vai buscar uma lista de convidados, so que vai buscar por Evento e vai receber esse evento fazendo busca no DB
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);

}
