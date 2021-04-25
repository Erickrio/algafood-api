package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;
//negócios(dominio) - O que uma cozinha tem que ter? 
@Repository//diz que e Repository do Spring data JPA
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	//cria um implementação em tempo real
	
	//metodo de consulta por nome (lista)
	//List<Cozinha> nome(String nome);
	
	//prefixo findBy + criterios (Exemplo) - findByExemplo
	//List<Cozinha> findByNome(String nome);
	
	//List<Cozinha> findQualquerCoisaByNome(String nome);
	
	//like %nome% = containing
	List<Cozinha> findTodosByNomeContaining(String nome);
	
	//retornar uma unica instância - retorna apenas uma. Mais de uma retorna exceção
	//Cozinha findBynome(String nome);
	
	Optional<Cozinha> findOptByNome(String nome);
	
	//existe = boolean
	
	boolean existsByNome(String nome);
}
