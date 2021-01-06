package com.algaworks.algafood.listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;
import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	//classe escuta os eventos que estamos disparando

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	@EventListener //método é um ouvinte do evento
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("Cliente"+event.getCliente().getNome() +"Seu cadastro no sistema está ativo!" );
	}
}
