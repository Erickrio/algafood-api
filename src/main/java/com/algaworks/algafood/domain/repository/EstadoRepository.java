package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Long id);//Ao invés de recebermos como parâmetro, um objeto do tipo da entidade, iremos receber o ID das mesmas.

}
