package com.euripedes.Conectando.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Diario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credito_id", nullable = false)
    private Conta credito;

    @ManyToOne
    @JoinColumn(name = "debito_id", nullable = false)
    private Conta debito;

    private Double valor;
    private LocalDate data;
    private String historico;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta getCredito() {
		return credito;
	}

	public void setCredito(Conta credito) {
		this.credito = credito;
	}

	public Conta getDebito() {
		return debito;
	}

	public void setDebito(Conta debito) {
		this.debito = debito;
	}

	public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

