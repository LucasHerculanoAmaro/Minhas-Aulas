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
	@Column(name = "cliente_id")
	private Long id;
	
	@Column(name = "nomeCliente")
	private String nomeCliente;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	public void cliente(Long id, String nomeCliente, String cnpj) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.cnpj = cnpj;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
