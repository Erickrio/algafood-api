package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//se excecão for lançada e nao tratada nos queremos que o retorno seja 404
@ResponseStatus(value = HttpStatus.NOT_FOUND) //,reason="Entidade Não encontrada") 
public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
