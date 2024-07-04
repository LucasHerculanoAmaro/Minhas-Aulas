package Optional;

import java.util.Optional;

public class EstudandoOptional {
	
	public static void main(String[] args) {
		
/*	INTRODUÇÃO 
 
 * 	É muito comum para os Desenvolvedores Java encontrar erros e 
  	precisar trabalhar encima deles para evitá-los. Mas pode acontecer 
  	de um(a) desenvolvedor(a) escrever muitas linhas para lidar com um 
  	erro e deixar o seu código muito extenso. 
  	
 * 	Por exemplo: Imagine que, para corrigir um erro, um(a) 
 	desenvolvedor(a) implemente uma lógica com diversas estruturas 
 	IF/ELSE para cada condição. 
 	Depedendo da estrutura, e do quão robusto for a aplicação, o(a) 
 	profissional levaria muito tempo para trabalhar a fim de evitar
  	todos os possíveis erros.	
  		
 * 	Por mais que a aplicação mostra-se funcional, existem maneiras 
  	mais simples e práticas de lidar erros.
 	
 	
 *	O Optional é uma Classe container que surgiu na versão 8 do Java,
  	com o objetivo de simplificar o código e facilitar o trabalho do(a) 
  	Profissional Java ao lidar com valores nulos.
 	Este recurso pertence ao pacote "java.util", usado para representar 
 	um valor que pode ou não estar presente. O Optional encapsula o 
 	retorno de métodos e informa se o valor está presente ou não.
 	
 *	Veja alguns motivos para usar o Optional:
 
 		1. Evitar NullPointerException -> Ao trabalhar com objetos, 
 		podemos encontrar objetos que tenham valores nulos.  Usar 
 		métodos Optional ajuda a evitar o erro "NullPointerException"
 		e trabalhar com valores possivelmente nulos.
 		
 		2. Código Legível -> Usar Optional tornará nosso código mais 
 		limpo e fácil de entender.
 		
 		3. Encapsulamento da lógica de ausência -> Ao usar o Optional,
 		encapsulamos a lógica de nulidade e atribuimos as operações de 
 		mapeamento e filtragem.
*/
		
// __________________________________________________________________ //

/*  
  	IMPLEMENTANDO OPTIONAL 
 
 	Temos diversas maneiras de criar instâncias de Optional.
 	Veremos abaixo alguns exemplos.			
*/		
	System.out.println("1. Conhecendo alguns métodos Optional:\n");
//	1. Optional.of(value)	
		
		// Criando uma Optional com valor não nulo
		Optional<String> OptionalNaoNulo = Optional.of("Hello World");//(null);
		System.out.println("Optional não nulo: " + OptionalNaoNulo);
		
		/*	Quando usamos o método 'of()' não podemos adicionar um 
		   	valor nulo.
		   	Caso executar com o valor nulo, teremos o erro
		   	NullPointerException.
		*/
		
//	2. Optional.ofNullable(value)
		
		// Criando uma Optional que aceite valores nulos
		Optional<String> OptionalSimNulo = Optional.ofNullable(null);//("Hello World!");
		System.out.println("Optional com nulo: " + OptionalSimNulo);
		
		/*	Quando usamos o método 'ofNullAble(value)' podemos 
		 	trabalhar com valores nulos ou vazios. 
		*/
		
//	3. Optional.empty()
		
		//Criando um Optional vazio
		Optional<String> OptionalVazio = Optional.empty();//("Hello World!");//(null);
		System.out.println("Optional com vazio: " + OptionalVazio);
		
		/*	Quando usamos o método 'empty()', não podemos utilizar 
		  	valores nulos, nem retornar um valor como no primeiro 
		  	exemplo.  
		*/
		
// __________________________________________________________________ //
		
		System.out.println("\n2. Praticando com IF/ELSE:\n");
/*	
  	PRATICANDO COM OUTROS EXEMPLOS 
 
 	Vamos escrever mais alguns exemplos para praticar.
 	O exemplo abaixo terá um tratamento, onde utilizmos a estrutura
 	IF/ELSE para definir se há algum valor presente.
*/		
		Optional<String> optional = Optional.of("Hello World!");
		
		// Verificando se há valor presente
		if (optional.isPresent()) {
			System.out.println("O valor presente é: " + optional.get());
		} else {
			System.out.println("O valor está ausente!");
		}
		
/*
*  	Neste exemplo, vamos usar uma função que chamamos de expressão 
  	Lambda. O que é?
  	
*	É uma função anônima ou sem declaração (não precisa de nome, 
 	tipo de retorno e modificador de acesso). A sua sintaxe é 
 	simples:
 		
 		(ARGUMENTO) -> (CORPO)
 		
 	Em nosso exemplo baixo, temos:
 	
 		(ARGUMENTO) -> (          CORPO         )
 		   value    ->  System.out.println("...")
  	
  	Essa função pode ser passada como argumento para um método ou 
  	armazenada em variáveis.
*/
		
		// Usando ifPresent para executar uma ação se o valor estiver presente
		optional.ifPresent(value -> System.out.println("Valor utilizando ifPresent com Lambda: " + value));
		
/*	
 	Agora veremos outro exemplo, mas dessa vez trabalharemos com 
 	os métodos 'orElse' e 'orElseGet'.
*/		
		System.out.println("\n2.1. Trabalhando com 'orElse()' e 'orElseGet()\n");
		
		// Criando Optional of() e empty()
		Optional<String> optionalComValor = Optional.of("Hello World!");
		Optional<String> optionalSemValor = Optional.empty();
		
		// Usando orElse para fornecer um valor padrão
		String valor1 = optionalComValor.orElse("Sem Valor");
		String valor2 = optionalSemValor.orElse("Sem Valor");
		
		System.out.println("Valor com orElse: " + valor1);
		System.out.println("Valor com orElse: " + valor2);
		
		// Usando orElse para fornecer um valor padrão através de um Supplier
		String valor3 = optionalComValor.orElseGet(() -> "Sem valor gerado");
		String valor4 = optionalSemValor.orElseGet(() -> "Sem valor gerado");
		
		System.out.println("Valor com orElseGet: " + valor3);
		System.out.println("Valor com orElseGet: " + valor4);
		
		//Criando uma excessão com orElseThrow para valores não presente
		System.out.println("\n2.2. Criando uma excessão com orElseThrow:\n");
		
		try {
			String valor = optionalSemValor.orElseThrow(() -> new IllegalStateException("Valor Ausente"));
			System.out.println("Valor: " + valor);
		} catch (IllegalStateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
}

/*	REFERÊNCIAS
  
 * 	Para que serve o Optional do Java 8? Como Usar?
  	https://pt.stackoverflow.com/questions/447672/para-que-serve-o-optional-do-java-8-como-usar
 
 *	Optional
 	https://github.com/Leon4rdoalves/CookBook-Java/blob/main/16.md
 
 *	Como usar funções Lambda em Java
 	https://www.devmedia.com.br/como-usar-funcoes-lambda-em-java/32826
  
 *	Optional no Java 8 e no Java 9
  	https://medium.com/@racc.costa/optional-no-java-8-e-no-java-9-7c52c4b797f1
 * */
 