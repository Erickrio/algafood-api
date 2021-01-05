package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Configuration //define bean
public class ServiceConfig {
	@Bean(initMethod ="init" , destroyMethod = "destroy")
	public AtivacaoClienteService ativacaoClienteService() {
		return new AtivacaoClienteService();
	}

}
