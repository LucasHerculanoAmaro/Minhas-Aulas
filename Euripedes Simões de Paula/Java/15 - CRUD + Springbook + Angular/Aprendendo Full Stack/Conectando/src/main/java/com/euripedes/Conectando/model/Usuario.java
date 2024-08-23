package com.euripedes.Conectando.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "idade")
    private int idade;
    
    @Column(name = "turma")
    private String turma;
    
    @Column(name = "profissao")
    private String profissao;
    
    @NotNull(message = "O atributo Usuário é Obrigatório!")
	private String usuario;
    
    public void usuario(Long id, String nome, int idade, String turma, String profissao, String usuario) {
    	this.id = id;
    	this.nome = nome;
    	this.idade = idade;
    	this.turma = turma;
    	this.profissao = profissao;
    	this.usuario = usuario;
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
}
