package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
		return cozinhaRepository.findAll();
	}
	
	//responseEntity - manipula a resposta
	@GetMapping("/{cozinhaId}")
	public ResponseEntity <Cozinha>buscar(@PathVariable Long cozinhaId) {
		//instancia (opcional) pode ter uma cozinha ou não. Evita Nullpoienter
		Optional<Cozinha> cozinha =  cozinhaRepository.findById(cozinhaId);
		
		//Pergunta ao Optional se tem cozinha 
		if(cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get()); 
		}		
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
		
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
		
		if(cozinhaAtual.isPresent()) {
			
		BeanUtils.copyProperties(cozinha, cozinhaAtual.get(),"id");
		Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());	
		return ResponseEntity.ok(cozinhaSalva);
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

