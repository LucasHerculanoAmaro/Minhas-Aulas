package Model;

public class User {

/*	

1. 	INTRODUÇÃO

*	Na aula de hoje criaremos um pacote com uma classe destinada ao objeto.
	
*	Esta classe contará com os atributos do objeto (id, nome, email), 
	um método construtor com parametros similares aos atributos e os métodos
	'Getters & Setters'.

*	Então, vamos trabalhar!
	
*/
	
//	Criando os Atributos do objeto
	private int id;
	private String nome;
	private String email;
	
//	Criando o método Construtor	
	public User(
			
//			Criando os parametros
			int id, 
			String nome, 
			String email
			) {
		
/*	OBS: Os parametros e os atributos não são a mesma coisa, o método 
	construtor precisa de parametros que referenciarão os atributos de um 
	objeto, mas você não poderá fazer referência direta à algum atributo do
	obeto sem um parametro.
	
*	Mesmo não sendo iguais, precisamos dizer que os dados dos atributos e 
 	dos parametros são os mesmos, e para isso, veja abaixo como configuramos.
*/			
		
//		Atributos	X	Parametros
		this.id 		= id;
		this.nome 		= nome;
		this.email 		= email;
		
/*	OBS: ao utilizar a palavra 'this', estamos indicando ao programa que se
	trata do atributo e não ao parametro. Perceba que, depois do sinal '=', 
	o parametro é escrito. 

*	Para resumir, primeiro referenciamos o atributo, agregando a si a palavra
	reservada 'this', depois utilizados o sinal '=' junto com o parametro.

*	Assim, sabemos que os atributos e parametros não são a mesma coisa, mas
	compartilham algo em comúm.
*/		
		
	}
	
/*	Após a criação do método construtor, vamos criar os métodos 'getters & 
	setters'. Estes métodos permitem compartilhar dados de variáveis com 
	modificadores do tipo 'private'.
 
*	Pode ser um risco criarmos variáveis do tipo 'public', já que elas podem 
	ser acessadas com facilidade, aumentando o risco de invasão em sua 
	aplicação, e a transmissão não autoziada de dados.

*	Estes métodos ajudam a deixar a aplicação mais segura; sem contar que 
	preciraremos desses métodos para mais tarde.

*	Como já estudamos sobre a sintaxe do método 'getters & setters', vamos
	partir para uma prática mais simples:
	
		1. Clique com o Botão direito do mouse dentro da classe User.
		2. Escolha a opção 'source'.
		3. Clique em 'Generate Getters and Setters'.
		4. Selecione todos os atributos em 'Select All'.
		5. Clique em 'Generate'.
		
*	Esta é uma maneira mais simples de criar os métodos 'getters & setters' 
	sem a necessidade de escrever palavra por palavra. Fazer assim deixa o 
	serviço mais simples, principalmente quando trabalhamos com objetos que 
	contém muitos atributos.
	
*	Veja abaixo como os métodos 'Getters & Setters' estão configurados.

*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
/*	CONCLUSÃO

*	Na próxima aula implementaremos o primeiros método CRUD, o CREATE.
	Acompanhe ela no pacote 'Dao'. Não se esqueça de criar o pacote 'Dao' 
	e a classe userDAO.
	
*	Até a próxima!
*/
	
}
