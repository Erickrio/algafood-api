package com.algaworks.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
public class NotificadorSMS implements Notificador {


	@Override
	public void notificar(Cliente cliente, String mensagem) {	
		System.out.printf("Notificando %s através do e-mail %s:usando SMTP %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}


}