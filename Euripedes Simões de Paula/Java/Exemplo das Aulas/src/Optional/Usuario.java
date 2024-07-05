package Optional;

// Criando a Classe Usuário
public class Usuario {

	// Atributos da Classe
	private Long id;
	private String nome;
	private int idade;
	
	// Construtor da Classe
	public Usuario(long id, String nome, int idade ) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	//Getters and Setters
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}
