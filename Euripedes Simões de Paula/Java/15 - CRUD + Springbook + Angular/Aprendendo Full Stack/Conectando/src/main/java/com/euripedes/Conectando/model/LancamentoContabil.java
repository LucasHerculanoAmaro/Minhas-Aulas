package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lancamento")
public class LancamentoContabil {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "contaId")
	private Conta contaId;

    @ManyToOne
    @JoinColumn(name = "conta_debito_id")
    private Conta codigoDebito;

    @ManyToOne
    @JoinColumn(name = "conta_credito_id")
    private Conta codigoCredito;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
    
    private String historico;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

	public LancamentoContabil(Long id, Conta codigoDebito, Conta codigoCredito, LocalDate data, String historico,
			BigDecimal valor, Cliente cliente) {
		super();
		this.id = id;
		this.codigoDebito = codigoDebito;
		this.codigoCredito = codigoCredito;
		this.data = data;
		this.historico = historico;
		this.valor = valor;
		this.cliente = cliente;
	}
	
	public LancamentoContabil() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getCodigoDebito() {
		return codigoDebito;
	}

	public void setCodigoDebito(Conta codigoDebito) {
		this.codigoDebito = codigoDebito;
	}

	public Conta getCodigoCredito() {
		return codigoCredito;
	}

	public void setCodigoCredito(Conta codigoCredito) {
		this.codigoCredito = codigoCredito;
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

	public Conta getContaId() {
		return contaId;
	}

	public void setContaId(Conta contaId) {
		this.contaId = contaId;
	}
    
    
}
