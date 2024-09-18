package com.euripedes.Conectando.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Balancete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "lancamento") // Nome da coluna na tabela balancete
    private LancamentoContabil lancamento;
    
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
    
    private BigDecimal valor = BigDecimal.ZERO;
    
    private BigDecimal valorDebito = BigDecimal.ZERO;
    private BigDecimal valorCredito = BigDecimal.ZERO;
    


	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
	public LancamentoContabil getLancamento() {
		return lancamento;
	}
	public void setLancamento(LancamentoContabil lancamento) {
		this.lancamento = lancamento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	public BigDecimal getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	
}
