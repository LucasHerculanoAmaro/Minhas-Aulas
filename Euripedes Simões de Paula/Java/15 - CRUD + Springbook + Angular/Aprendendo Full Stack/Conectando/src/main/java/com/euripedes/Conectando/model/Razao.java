package com.euripedes.Conectando.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Razao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@OneToMany(mappedBy = "razao")
	private List<Diario> diarios;
	
	private BigDecimal debito;
	private BigDecimal credito;
	private LocalDate data;
	private String historico;
	
    @ManyToOne
    @JoinColumn(name = "diario_id")  // Especifica a chave estrangeira que se refere ao ID do Diário
    private Diario diario;  // Relacionamento com a entidade Diário
	
	public Razao() {
		
	}
	
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
	public List<Diario> getDiarios() {
		return diarios;
	}
	public void setDiarios(List<Diario> diarios) {
		this.diarios = diarios;
	}
	public BigDecimal getDebito() {
		return debito;
	}
	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}
	public BigDecimal getCredito() {
		return credito;
	}
	public void setCredito(BigDecimal credito) {
		this.credito = credito;
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
	public Diario getDiario() {
		return diario;
	}
	public void setDiario(Diario diario) {
		this.diario = diario;
	}


	
	
	
}
