package com.algaworks.algafood.di.notificacao;


import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
//@Qualifier("email") //anotação + identificador(email)
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {


	@Override
	public void notificar(Cliente cliente, String mensagem) {	
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}


}
