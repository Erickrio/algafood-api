package com.algaworks.algafood.infrastrucure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
@Repository
public class ResturanteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante",Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
		
	}

}
