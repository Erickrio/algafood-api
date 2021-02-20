package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/teste") //requisicao HTTP /teste
public class TesteController {

	//injeta o repositorio de cozinha
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodosByNome(nome);
	}
	
	@GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> cozinhaPorNome( String nome){
		return cozinhaRepository.findOptByNome(nome);
	}
}
