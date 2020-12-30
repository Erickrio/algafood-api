package com.algaworks.algafood.di.service;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NotificadorEmail;
@Component //essa classe é um bean
public class AtivacaoClienteService {
	
	//dependencia com notificador email é sempre null pois ninguem instancia - null pointer exception
	private NotificadorEmail notificador;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	}
	

}
