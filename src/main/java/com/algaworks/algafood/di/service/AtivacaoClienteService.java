package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
@Component
public class AtivacaoClienteService {
	
	//ponto de injecao - atributo (AS VEZES É USADO AQUI ESSA INJEÇÃO - AUTOWIRED)
	@Autowired
	private Notificador notificador;
	
	/*1 ponto de injeção - construtor (IDEAL É QUE USE A INJEÇÃO DE DEPENDENCIA AQUI)
	@Autowired //2 ponto de injeção - @Autowired define qual construtor deve usar.
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	} */
	
	/*
	public AtivacaoClienteService(String exemplo) {
		//construtor exemplo - spring  não consegue identificar qualé o padrão
	}*/
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	}

	public Notificador getNotificador() {
		return notificador;
	}

	/*
	//3 ponto de injecao - setetter - precisa por anotação Autowired,se não dá nullpoienter
	@Autowired
	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	} */
	

}
