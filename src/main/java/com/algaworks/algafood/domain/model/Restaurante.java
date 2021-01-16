package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
public class Restaurante {
	@EqualsAndHashCode.Include //Apenas o id seja usado nos métodos equals e hashCode.
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name= "taxa_Frete" , nullable = false) 
	private BigDecimal taxaFrete;
	
	//restaurante possui uma cozinha
	
	@ManyToOne //muitos restaurantes possui uma cozinha
	@JoinColumn (name="cozinha_id")
	private Cozinha cozinha;

	
	
}
