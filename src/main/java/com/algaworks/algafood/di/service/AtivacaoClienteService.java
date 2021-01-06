package com.algaworks.algafood.di.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {
    //publica eventos
	@Autowired//é injetado
   	private ApplicationEventPublisher eventPublisher;
   	
   	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		//dispara um evento para todo sistema que diz que o cliente foi ATIVADO
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
}

}
