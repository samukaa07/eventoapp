package com.eventosapp.a.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventosapp.models.Evento;

//<Evento,String> significa a entidade
	// essa interface vamos estanciar ela e utilizar emetodos prontos , salvar, deletar, update e etc
public interface EventoRepository extends CrudRepository<Evento, String>{

	 Evento findByCodigo(long codigo);


}
