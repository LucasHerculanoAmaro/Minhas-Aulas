package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class LancamentoContabil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long contaId;
	private LocalDate data;
	private String  descricao;
	private BigDecimal valor;
	
	@ManyToOne
    private Conta contaDebito;
	
	@ManyToOne
    private Conta contaCredito;
	
	@OneToOne
	private Transacao transacaoDebito;
	
	@OneToOne
	private Transacao transacaoCredito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Conta getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(Conta contaDebito) {
		this.contaDebito = contaDebito;
	}

	public Conta getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Conta contaCredito) {
		this.contaCredito = contaCredito;
	}

	public Transacao getTransacaoDebito() {
		return transacaoDebito;
	}

	public void setTransacaoDebito(Transacao transacaoDebito) {
		this.transacaoDebito = transacaoDebito;
	}

	public Transacao getTransacaoCredito() {
		return transacaoCredito;
	}

	public void setTransacaoCredito(Transacao transacaoCredito) {
		this.transacaoCredito = transacaoCredito;
	}

	
	
	
	
}
