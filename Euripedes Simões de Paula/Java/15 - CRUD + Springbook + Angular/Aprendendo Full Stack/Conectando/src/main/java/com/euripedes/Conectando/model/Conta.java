package com.euripedes.Conectando.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigo;  // Código da conta, ex: "1111" para Caixa, "2222" para Fornecedores
	private String nome;    // Nome da conta, ex: "Caixa", "Fornecedores"
	private String tipo;    // Tipo da conta, ex: "Débito" ou "Crédito" (ou patrimonial e resultado, dependendo do caso)
	 
	public Conta(Long id, String codigo, String nome, String tipo) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	

	
	
}
