package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data ////tem getter,setter,equals e hashcode (lambok)
@EqualsAndHashCode(onlyExplicitlyIncluded=true)//somente deixar explicito em um atributo
@Entity
public class Restaurante {
	@EqualsAndHashCode.Include //Apenas o id seja usado nos métodos equals e hashCode. (//inclui explicitamente aqui)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //autoIncremento
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name= "taxa_Frete" , nullable = false) 
	private BigDecimal taxaFrete;
	
	//restaurante possui uma cozinha
	
	@ManyToOne //muitos restaurantes possui uma cozinha
	@JoinColumn (name="cozinha_id",nullable = false)// restrições para não permitir valores nulos nos atributos de restaurante
	private Cozinha cozinha;
	
	@JsonIgnore //Não traz o endereco na requisicao Restaurante
	@Embedded //é uma parte da classe restaurante
	private Endereco endereco;
	
	
	//dt cadastro e atualização
	//nullable - indica que a propriedade é obrigatoria
	//@CreationTimestamp - dt atual
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable=false)
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable=false)
	private LocalDateTime dataAtualizacao;
	
	//muitos restaurantes possui muitas formas de pagamento
	//joincolumn- define o nome da coluna da tab intermediaria da tab restaurante
    //inverso - tab forma de Pagamento
	//JsonIgnore --gera as representações s forma de pagamento
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento>formasPagamento = new ArrayList<>();

	
	
}
