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

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(cozinhaRepository.listar());
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
		//retorna a REPRESENTAÇÂO desse recurso no corpo da resposta
		return cozinhaRepository.salvar(cozinha);
		
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
		cozinhaRepository.salvar(cozinhaAtual);	
		return ResponseEntity.ok(cozinhaAtual);
        }
		//caso contrário retorne notFound (não encontrado)
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha>remover(@PathVariable Long cozinhaId){
		//Tratando erro de -  Cannot delete or update a parent row: a foreign key constraint fails (`algafood`.`restaurante`, CONSTRAINT )
		
		try {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if (cozinha != null) {
		cozinhaRepository.remover(cozinha);
		
		//HttpStatus.NO_CONTENT - retorna s corpo - SUCESSO
		return ResponseEntity.noContent().build();
	}
	
		//caso contrario retorne recurso não existe (notFound)
		return ResponseEntity.notFound().build();
	}catch(DataIntegrityViolationException e) {
		//Satus 400 - Bad Request é correto também.
		//HttpStatus.CONFLICT(erro do cliente) - Ideal é retornar um corpo dizendo qual foi o problema que gerou esse conflito
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
}
	
}


