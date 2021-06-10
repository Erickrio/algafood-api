package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
//se excecão for lançada e nao tratada nos queremos que o retorno seja 404
//@ResponseStatus(value = HttpStatus.NOT_FOUND) //,reason="Entidade Não encontrada") 
public class EntidadeNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
		super(status, mensagem);
		// TODO Auto-generated constructor stub
	}

	
	public EntidadeNaoEncontradaException(String mensagem) {
		this(HttpStatus.NOT_FOUND,mensagem);
	}
	
}
