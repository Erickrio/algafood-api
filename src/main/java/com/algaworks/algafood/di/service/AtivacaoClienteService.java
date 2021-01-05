package com.algaworks.algafood.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;
//@Component
public class AtivacaoClienteService {
//problema de ambuiguidade é que o spring n sabe qual que executa (email ou sms)??

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	//1 - CICLO DE VIDA /  É CHAMADO DEPOIS DO CONSTRUTOR
	
//	@PostConstruct  
//	public void init() {
//		System.out.println("INIT...." + notificador);
//	}
	
	public void init() {
		System.out.println("INIT...." + notificador);
	}
	
	//antes de ser destroido é chamado o bean
	//@PreDestroy
	public void destroy() {
		System.out.print("DESTROY");
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	
}

}
