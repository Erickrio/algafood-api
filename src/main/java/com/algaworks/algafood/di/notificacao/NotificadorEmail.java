package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
@Qualifier("email") //anotação + identificador(email)
@Component
public class NotificadorEmail implements Notificador {


	@Override
	public void notificar(Cliente cliente, String mensagem) {	
		System.out.printf("Notificando %s por SMS através do telefone %s: %s\n", 
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}


}
