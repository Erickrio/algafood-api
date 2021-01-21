package com.algaworks.algafood.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity; //javax vem do JPA
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("gastronomia")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore// ignora o titulo - Representacao
	@JsonProperty("titulo") //não muda o nome do modelo de dominio mas sim a REPRESENTAÇÃO
	@Column(nullable = false)
	private String nome;
}
