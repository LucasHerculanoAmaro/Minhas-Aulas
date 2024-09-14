package com.euripedes.Conectando.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Balancete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "lancamento_id")
	private Long lancamentoId;
	
    private Conta codigoConta;
   // private String codigoConta;
    private BigDecimal saldoDevedor = BigDecimal.ZERO;
    private BigDecimal saldoCredor = BigDecimal.ZERO;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLancamentoId() {
		return lancamentoId;
	}
	public void setLancamentoId(Long lancamentoId) {
		this.lancamentoId = lancamentoId;
	}
	public Conta getCodigoConta() {
		return codigoConta;
	}
	public void setCodigoConta(Conta codigoConta) {
		this.codigoConta = codigoConta;
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


	
}
