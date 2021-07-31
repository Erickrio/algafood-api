package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		return restauranteRepository.findAll();	
	}
	
//	//lista por ID
//	@GetMapping("/{restauranteId}")
//	public ResponseEntity<Restaurante>buscar(@PathVariable Long restauranteId){
//	Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
//		
//		if (restaurante.isPresent()) {
//			return ResponseEntity.ok(restaurante.get());
//		}
//		
//	   return ResponseEntity.notFound().build();	
//	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId){
	   return cadastroRestaurante.buscarOuFalhar(restauranteId);
	}
	
	
//	//@ResponseStatus(HttpStatus.CREATED)
//	//coringa ? -pode ser qualquer coisa(restaurante) = getMessage
//	@PostMapping
//	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
//		try {
//			restaurante = cadastroRestaurante.salvar(restaurante);
//			return ResponseEntity.status(HttpStatus.CREATED)
//					.body(restaurante);
//		} catch(EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//		
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {

		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	/*
	 * MÉTODO DE ATUALIZAÇÃO
	 * @PathVariable - especifica que o parâmetro fará parte da URL
	 * @RequestBody  obtem valores do body da requisição.
	 * @PutMapping mapear nosso endpoint para esse verbo, com esse path
	 */
//	@PutMapping("/{restauranteId}")
//	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
//			@RequestBody Restaurante restaurante) {
//		try {
//			Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);
//			//cópia das propriedades do objeto Restaurante
//			if (restauranteAtual != null) {
//			// add propriedade para ser ignorada
//				BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formasPagamento",
//						"endereco","dataCadastro","produtos");
//				
//				restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
//				return ResponseEntity.ok(restauranteAtual);
//			}
//			
//			return ResponseEntity.notFound().build();
//		
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest()
//					.body(e.getMessage());
//		}
//	}
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {

		try {

			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
					"produtos");
			return cadastroRestaurante.salvar(restauranteAtual);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}
	
	@PatchMapping("/{restauranteId}")//atualiza apenas 1 atributo 
	public Restaurante atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {
		//busca no restaurante
//		Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

		//Função do merge mesclar os valores que está no campo (campos) - dentro do restauranteAtual
		merge(campos, restauranteAtual);
		//chama o metodo atualizar passando o restauranteId e o restaurante atual para salvar
		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		//converte objetos java em JSON - vise versa
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		System.out.println(restauranteOrigem);
		
		//imrpime oq ta dentro do mapa - função lambda (loop)
		dadosOrigem.forEach((nomePropriedade,valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			//busca o valor da propriedade (field) dentro da instancia resturantrOrigem 
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			//novo valor convertido pelo objectMapper
			//System.out.println(nomePropriedade + "=" + valorPropriedade + "="+novoValor);
			
			//pegar os valores do restauranteOrigem e atribuir ao restauranteDestino
			  ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
		
	}

}
