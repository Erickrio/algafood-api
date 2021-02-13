package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;
//negócios(dominio) - O que uma cozinha tem que ter? 
@Repository//diz que e Repository do Spring data JPA
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {

	//cria um implementação em tempo real
	
	//List<Cozinha> consultarPorNome(String nome);

}
