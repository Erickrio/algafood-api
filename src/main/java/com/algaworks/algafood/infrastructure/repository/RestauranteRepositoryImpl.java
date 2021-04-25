package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;
	
	//circular reference - loop infinito resolve - LAzy (preguiçoso) deixa p frente..
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome,BigDecimal taxaFreteInicial,BigDecimal taxaFreteFinal){
		var builder = manager.getCriteriaBuilder();
		
		var criteria = builder.createQuery(Restaurante.class);
		var root = criteria.from(Restaurante.class);
		
		//Lista de predicates
		var predicates = new ArrayList<Predicate>();
		
		//se tiver texto na var nome
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%" ));
		}
		
		if (taxaFreteInicial != null) {
			predicates.add( builder
					.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		
		if (taxaFreteFinal != null) {
			predicates.add( builder
					.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		
		//busca só por nome,taxainicial e final
		//converter lista em array -use toArray(instancia vazia[0])
		criteria.where(predicates.toArray(new Predicate[0])); 
		
		var query = manager.createQuery(criteria);
		return query.getResultList();
		
					
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
}
