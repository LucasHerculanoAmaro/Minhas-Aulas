package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LancamentoContabilDto {
    private Long contaDebitoId;
    private Long contaCreditoId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String historico;
    private BigDecimal valor;
    
	public Long getContaDebitoId() {
		return contaDebitoId;
	}
	public void setContaDebitoId(Long contaDebitoId) {
		this.contaDebitoId = contaDebitoId;
	}
	public Long getContaCreditoId() {
		return contaCreditoId;
	}
	public void setContaCreditoId(Long contaCreditoId) {
		this.contaCreditoId = contaCreditoId;
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
