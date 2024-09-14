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
    
    @Column(name = "lancamento_id")
    private Long lancamentoId;
    
    @ManyToOne
    @JoinColumn(name = "lancamento") // Nome da coluna na tabela balancete
    private LancamentoContabil lancamento;
    
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
    
   // @Column(name = "codigo")
    private String codigo;
    
    private BigDecimal valor = BigDecimal.ZERO;
    
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
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
