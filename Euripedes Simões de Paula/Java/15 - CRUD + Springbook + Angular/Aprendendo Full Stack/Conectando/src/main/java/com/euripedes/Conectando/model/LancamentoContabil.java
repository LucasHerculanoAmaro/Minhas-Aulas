package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lancamento")
public class LancamentoContabil {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "debito_id")
    private Conta debitoId;

    @ManyToOne
    @JoinColumn(name = "credito_id")
    private Conta creditoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
    
    private String historico;
    
    private BigDecimal valor;
	
	public LancamentoContabil(Long id, Conta debitoId, Conta creditoId, LocalDate data, String historico, BigDecimal valor) {
		super();
		this.id = id;
		this.debitoId = debitoId;
		this.creditoId = creditoId;
		this.data = data;
		this.historico = historico;
		this.valor = valor;
	}

	public LancamentoContabil() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getDebitoId() {
		return debitoId;
	}

	public void setDebitoId(Conta debitoId) {
		this.debitoId = debitoId;
	}

	public Conta getCreditoId() {
		return creditoId;
	}

	public void setCreditoId(Conta creditoId) {
		this.creditoId = creditoId;
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

	

    
}
