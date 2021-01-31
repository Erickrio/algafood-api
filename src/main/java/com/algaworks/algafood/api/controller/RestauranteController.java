package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value ="/restaurantes")
public class RestauranteController {
	
	//injetar uma propriedade do tipo RestauranteRepository
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	//listando todos os restaurantes
	@GetMapping
	public List<Restaurante>listar(){
		return restauranteRepository.listar();	
	}
	
	//lista por ID
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante>buscar(@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);
		
		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		
	   return ResponseEntity.notFound().build();	
	}
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	//coringa ? -pode ser qualquer coisa(restaurante) = getMessage
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	/*
	 * MÉTODO DE ATUALIZAÇÃO
	 * @PathVariable - especifica que o parâmetro fará parte da URL
	 * @RequestBody  obtem valores do body da requisição.
	 * @PutMapping mapear nosso endpoint para esse verbo, com esse path
	 */
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

			if (restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

				restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos){
		
		//busca no restaurante
		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
		
		
		//se restaurante atual for nula
		
		if (restauranteAtual != null) {
			return ResponseEntity.notFound().build(); 
		}
		
		//Função do merge mesclar os valores que está no campo (campos) - dentro do restauranteAtual
		merge(campos, restauranteAtual);
		

		//chama o metodo atualizar passando o restauranteId e o restaurante atual para salvar
		return atualizar(restauranteId,restauranteAtual );
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		//imrpime oq ta dentro do mapa - função lambda (loop)
		camposOrigem.forEach((nomePropriedade,valorPropriedade) -> {
		//	if(nomePropriedade.equals("nome")) {
		//		restauranteDestino.setNome((String)valorPropriedade);
		//	} TODO - IREMOS USAR OUTRA FORMA AO INVÉS DESSA
			
			System.out.println(nomePropriedade + "=" + valorPropriedade);
		});
	}

}
