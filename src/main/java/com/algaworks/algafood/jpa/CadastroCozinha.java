package com.algaworks.algafood.jpa;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	/*Responsavel pelos comandos que damos no sql.
	  @para injetar poderia ser o @autowired,
	  melhor pratica serio persistencecontext  */
	
	@PersistenceContext // injeta o entity manager (salvar o objeto no bd...)
	private EntityManager manager;

	// Lista todas as cozinhas do bd.
	public List<Cozinha> listar(){
		//consulta em objetos do JPA e não em tabelas. 
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	
	
	}
}
