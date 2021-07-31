package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não Existe um cadastro de restaurante com código %d", restauranteId));
	}


}
