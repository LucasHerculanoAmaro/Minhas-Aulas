package com.euripedes.Conectando.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nomeCliente;
	
	@Column(name = "cnpj")
	private int cnpj;
	
	@Column(name = "codConta")
	private int codConta;
	
	@Column(name = "nomeConta")
	private String nomeConta;
	
	public void conta(Long id, String nomeCliente, int cnpj, int codConta, String nomeConta) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.cnpj = cnpj;
		this.codConta = codConta;
		this.nomeConta = nomeConta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public int getCodConta() {
		return codConta;
	}

	public void setCodConta(int codConta) {
		this.codConta = codConta;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}
	
	
	
}
