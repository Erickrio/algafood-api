package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
@Component
public class AtivacaoClienteService {
//problema de ambuiguidade é que o spring n sabe qual que executa (email ou sms)??
	@Qualifier("email") //diz ao spring usar o bean que tem qualificador email
	@Autowired
	private Notificador notificador;
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	
}

}
