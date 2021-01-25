package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}
	
	//responseEntity - manipula a resposta
	@GetMapping("/{cozinhaId}")
	public ResponseEntity <Cozinha>buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha =  cozinhaRepository.buscar(cozinhaId);
		
		//se a cozinha existe no repositorio 
		if(cozinha != null) {
			//retorna ok - corpo da reposta a representação da cozinha
			return ResponseEntity.ok(cozinha); 
		}
		
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		//retorna s corpo - notFound
		return ResponseEntity.notFound().build(); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
		
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha>atualizar(@PathVariable Long cozinhaId,
			@RequestBody Cozinha cozinha){
		
		//busca cozinha existente
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		
		/* Precisamos copiar os valores da instancia da cozinha para dentro da atual
		// 1 forma de se fazer : se tivessemos 10 atributos ,precisariamos fazer para todos eles. 
		 * cozinhaAtual.setNome(cozinha.getNome());
		 * cozinhaAtual.setDescricao(cozinha.getDescricao());
		 * /Forma de facilitar - use o BeanUtils. (origem(cozinha) e target(destino para cozinhaAtual) - FACILITA quando temos muitos propriedades 
		 */
		
		//Correção do erro Target must not be null - 
		if(cozinhaAtual != null) {
			
		BeanUtils.copyProperties(cozinha, cozinhaAtual,"id");
		cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);	
		return ResponseEntity.ok(cozinhaAtual);
        }
		//caso contrário retorne notFound (não encontrado)
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha>remover(@PathVariable Long cozinhaId){
		
   try {
	   //Pede a classe de serviço (cadastrocozinha) para excluir a cozinha 
	   cadastroCozinha.excluir(cozinhaId);
	   //sucesso = retorna noContent
		return ResponseEntity.noContent().build();
		
   } catch(EntidadeNaoEncontradaException e) {
	   return ResponseEntity.notFound().build();
	   //Dando tudo errado - entra nas condições do catch
	}catch(EntidadeEmUsoException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
   }

}

