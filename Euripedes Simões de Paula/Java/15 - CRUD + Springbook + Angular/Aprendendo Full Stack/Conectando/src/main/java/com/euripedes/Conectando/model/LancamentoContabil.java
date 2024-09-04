package com.euripedes.Conectando.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class LancamentoContabil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private String  descricao;
	
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
