package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
	//negócios(dominio) - O que uma cozinha tem que ter? 
	//PADRAO - repository é criado por agregado(agregate roo) e não por entidade
	
	List<Cozinha> listar(); //permitir  listar cozinha
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);

}
