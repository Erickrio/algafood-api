package com.algaworks.algafood.api.controller;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs;

@RestController
@RequestMapping("/teste") //requisicao HTTP /teste
public class TesteController {

	//injeta o repositorio de cozinha
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> cozinhasPorNome(String nome){
//		return cozinhaRepository.findTodosByNome(nome);
//	}
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodosByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> cozinhaPorNome( String nome){
		return cozinhaRepository.findOptByNome(nome);
	}
	
	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExits( String nome){
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> cozinhaPorTaxaFrete( 
			BigDecimal taxaInicial,BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> cozinhaPorTaxaFrete( 
			String nome, Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}	
	
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantePrimeiroPorNome( String nome){
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> cozinhaPorTaxaFrete( String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantePorNomeFrete( String nome,
			BigDecimal taxaFreteInicial,BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome,taxaFreteInicial,taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int restauranteCountPorCozinha( Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
	
		return restauranteRepository.findAll(comFreteGratis().
				and(comNomeSemelhante(nome)));
		
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
		return restauranteRepository.buscarPrimeiro();
	}
	
	@GetMapping("/cozinhas/primeira")
	public Optional<Cozinha> cozinhaPrimeiro() {
		return cozinhaRepository.buscarPrimeiro();
	}
}
