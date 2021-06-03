package com.algaworks.algafood.domain.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	//1 cozinha tem muitos restaurantes
	// new ArrayList<>(); - inicializa como uma lista vazia para evitar nullpointer
	//mappedBy - Em restaurante qual propriedade que mapea x'a cozinha
	//jsonIgonre - ignora a serialização -loop
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante>restaurantes = new ArrayList<>();
}
