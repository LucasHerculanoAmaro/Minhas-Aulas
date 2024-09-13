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
//	@Column(name = "cliente_id")
	private Long clienteId;
	
	@Column(name = "nomeCliente")
	private String nomeCliente;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	public void cliente(Long clienteId, String nomeCliente, String cnpj) {
		this.clienteId = clienteId;
		this.nomeCliente = nomeCliente;
		this.cnpj = cnpj;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
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
