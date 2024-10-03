package Model;

public class User {

/*	

1. 	INTRODU��O

*	Na aula de hoje criaremos um pacote com uma classe destinada ao objeto.
	
*	Esta classe contar� com os atributos do objeto (id, nome, email), 
	um m�todo construtor com parametros similares aos atributos e os m�todos
	'Getters & Setters'.

*	Ent�o, vamos trabalhar!
	
*/
	
//	Criando os Atributos do objeto
	private int id;
	private String nome;
	private String email;
	
//	Criando o m�todo Construtor	
	public User(
			
//			Criando os parametros
			int id, 
			String nome, 
			String email
			) {
		
/*	OBS: Os parametros e os atributos n�o s�o a mesma coisa, o m�todo 
	construtor precisa de parametros que referenciar�o os atributos de um 
	objeto, mas voc� n�o poder� fazer refer�ncia direta � algum atributo do
	obeto sem um parametro.
	
*	Mesmo n�o sendo iguais, precisamos dizer que os dados dos atributos e 
 	dos parametros s�o os mesmos, e para isso, veja abaixo como configuramos.
*/			
		
//		Atributos	X	Parametros
		this.id 		= id;
		this.nome 		= nome;
		this.email 		= email;
		
/*	OBS: ao utilizar a palavra 'this', estamos indicando ao programa que se
	trata do atributo e n�o ao parametro. Perceba que, depois do sinal '=', 
	o parametro � escrito. 

*	Para resumir, primeiro referenciamos o atributo, agregando a si a palavra
	reservada 'this', depois utilizados o sinal '=' junto com o parametro.

*	Assim, sabemos que os atributos e parametros n�o s�o a mesma coisa, mas
	compartilham algo em com�m.
*/		
		
	}
	
/*	Ap�s a cria��o do m�todo construtor, vamos criar os m�todos 'getters & 
	setters'. Estes m�todos permitem compartilhar dados de vari�veis com 
	modificadores do tipo 'private'.
 
*	Pode ser um risco criarmos vari�veis do tipo 'public', j� que elas podem 
	ser acessadas com facilidade, aumentando o risco de invas�o em sua 
	aplica��o, e a transmiss�o n�o autoziada de dados.

*	Estes m�todos ajudam a deixar a aplica��o mais segura; sem contar que 
	preciraremos desses m�todos para mais tarde.

*	Como j� estudamos sobre a sintaxe do m�todo 'getters & setters', vamos
	partir para uma pr�tica mais simples:
	
		1. Clique com o Bot�o direito do mouse dentro da classe User.
		2. Escolha a op��o 'source'.
		3. Clique em 'Generate Getters and Setters'.
		4. Selecione todos os atributos em 'Select All'.
		5. Clique em 'Generate'.
		
*	Esta � uma maneira mais simples de criar os m�todos 'getters & setters' 
	sem a necessidade de escrever palavra por palavra. Fazer assim deixa o 
	servi�o mais simples, principalmente quando trabalhamos com objetos que 
	cont�m muitos atributos.
	
*	Veja abaixo como os m�todos 'Getters & Setters' est�o configurados.

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
	
/*	CONCLUS�O

*	Na pr�xima aula implementaremos o primeiros m�todo CRUD, o CREATE.
	Acompanhe ela no pacote 'Dao'. N�o se esque�a de criar o pacote 'Dao' 
	e a classe userDAO.
	
*	At� a pr�xima!
*/
	
}
