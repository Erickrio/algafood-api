package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		//instancia de cozinha repository
		RestauranteRepository alteracaoRestaurante = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante r1 = new Restaurante();
		r1.setId(1l);
		r1.setNome("Toca do Boi");
		
		alteracaoRestaurante.salvar(r1);
		System.out.printf("%d - %s\n", r1.getId(), r1.getNome());
		
	}
	

}
