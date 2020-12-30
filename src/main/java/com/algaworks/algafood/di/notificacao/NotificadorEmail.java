package com.algaworks.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
//Spring gerencia essa classe (componente BEAN spring)
@Component
public class NotificadorEmail {
	
	
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n",
		cliente.getNome(),cliente.getEmail(),mensagem);
	}
}
