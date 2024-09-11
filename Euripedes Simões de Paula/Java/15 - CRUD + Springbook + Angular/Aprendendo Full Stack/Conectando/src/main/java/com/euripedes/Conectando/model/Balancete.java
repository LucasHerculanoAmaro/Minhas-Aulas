package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Balancete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String contaId;
	private String nomeConta;
	
	private LocalDate data;
	
	private BigDecimal saldoDevedor;
	private BigDecimal saldoCredor;
	private BigDecimal totalGeral;
	
	@OneToMany
	private List<RazaoAnalitico> razaoAnaliticos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContaId() {
		return contaId;
	}

	public void setContaId(String contaId) {
		this.contaId = contaId;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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

	public List<RazaoAnalitico> getRazaoAnaliticos() {
		return razaoAnaliticos;
	}

	public void setRazaoAnaliticos(List<RazaoAnalitico> razaoAnaliticos) {
		this.razaoAnaliticos = razaoAnaliticos;
	}
	
	
	
	
}
