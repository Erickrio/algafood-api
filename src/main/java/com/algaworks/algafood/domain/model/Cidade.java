package com.algaworks.algafood.domain.model;

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
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Cidade {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	//cidade tem um estado
	@ManyToOne 
	@JoinColumn (nullable = false)
	private Estado estado;

}
