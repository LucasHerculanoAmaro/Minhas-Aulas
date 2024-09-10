package com.euripedes.Conectando.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Balancete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigoConta;
	private String nomeConta;
	private BigDecimal saldoDevedor;
	private BigDecimal saldoCredor;
	private BigDecimal totalGeral;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoConta() {
		return codigoConta;
	}
	public void setCodigoConta(String codigoConta) {
		this.codigoConta = codigoConta;
	}
	public String getNomeConta() {
		return nomeConta;
	}
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}
	public BigDecimal getSaldoDevedor() {
		return saldoDevedor;
	}
	public void setSaldoDevedor(BigDecimal saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
	public BigDecimal getSaldoCredor() {
		return saldoCredor;
	}
	public void setSaldoCredor(BigDecimal saldoCredor) {
		this.saldoCredor = saldoCredor;
	}
	public BigDecimal getTotalGeral() {
		return totalGeral;
	}
	public void setTotalGeral(BigDecimal totalGeral) {
		this.totalGeral = totalGeral;
	}
	
	
}
