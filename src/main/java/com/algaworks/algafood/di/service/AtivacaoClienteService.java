package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
@Component
public class AtivacaoClienteService {
	//dependencia obrigatoria - sem isso n funciona.
	@Autowired
	private Notificador notificador;
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		if(notificador != null) {
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	}else {
		System.out.println("Não existe notificador,mas o cliente foi notificado");
	}

}

}
