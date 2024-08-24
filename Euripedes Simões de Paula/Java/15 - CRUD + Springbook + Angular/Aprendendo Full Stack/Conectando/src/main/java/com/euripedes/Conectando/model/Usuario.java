package com.euripedes.Conectando.model;

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
    
	//@NotNull(message = "O atributo Usuário é Obrigatório!")
	private String usuario;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "foto")
	private String foto;
	
	private String tipo;
    
    public void usuario(Long id, String nome, int idade, String turma, String profissao, String usuario, String senha, String tipo, String foto) {
    	this.id = id;
    	this.nome = nome;
    	this.idade = idade;
    	this.turma = turma;
    	this.profissao = profissao;
    	this.usuario = usuario;
    	this.senha = senha;
    	this.foto = foto;
    	this.tipo = tipo;
    }

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
    
}
