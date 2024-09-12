package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class LancamentoContabil {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_debito_id")
    private Conta contaDebito;

    @ManyToOne
    @JoinColumn(name = "conta_credito_id")
    private Conta contaCredito;

    private LocalDate data;
    private String historico;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

	public LancamentoContabil(Long id, Conta contaDebito, Conta contaCredito, LocalDate data, String historico,
			BigDecimal valor, Cliente cliente) {
		super();
		this.id = id;
		this.contaDebito = contaDebito;
		this.contaCredito = contaCredito;
		this.data = data;
		this.historico = historico;
		this.valor = valor;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
    
}
