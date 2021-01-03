package com.algaworks.algafood.di.service;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;

public class AtivacaoClienteService {
	
	private Notificador notificador;
	
	//1 ponto de injeção - construtor 
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	}
	

}
