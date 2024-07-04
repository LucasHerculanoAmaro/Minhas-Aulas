package Optional;

import java.util.Optional;

public class EstudandoOptional {
	
	public static void main(String[] args) {
		
/*	INTRODU��O 
 
 * 	� muito comum para os Desenvolvedores Java encontrar erros e 
  	precisar trabalhar encima deles para evit�-los. Mas pode acontecer 
  	de um(a) desenvolvedor(a) escrever muitas linhas para lidar com um 
  	erro e deixar o seu c�digo muito extenso. 
  	
 * 	Por exemplo: Imagine que, para corrigir um erro, um(a) 
 	desenvolvedor(a) implemente uma l�gica com diversas estruturas 
 	IF/ELSE para cada condi��o. 
 	Depedendo da estrutura, e do qu�o robusto for a aplica��o, o(a) 
 	profissional levaria muito tempo para trabalhar a fim de evitar
  	todos os poss�veis erros.	
  		
 * 	Por mais que a aplica��o mostra-se funcional, existem maneiras 
  	mais simples e pr�ticas de lidar erros.
 	
 	
 *	O Optional � uma Classe container que surgiu na vers�o 8 do Java,
  	com o objetivo de simplificar o c�digo e facilitar o trabalho do(a) 
  	Profissional Java ao lidar com valores nulos.
 	Este recurso pertence ao pacote "java.util", usado para representar 
 	um valor que pode ou n�o estar presente. O Optional encapsula o 
 	retorno de m�todos e informa se o valor est� presente ou n�o.
 	
 *	Veja alguns motivos para usar o Optional:
 
 		1. Evitar NullPointerException -> Ao trabalhar com objetos, 
 		podemos encontrar objetos que tenham valores nulos.  Usar 
 		m�todos Optional ajuda a evitar o erro "NullPointerException"
 		e trabalhar com valores possivelmente nulos.
 		
 		2. C�digo Leg�vel -> Usar Optional tornar� nosso c�digo mais 
 		limpo e f�cil de entender.
 		
 		3. Encapsulamento da l�gica de aus�ncia -> Ao usar o Optional,
 		encapsulamos a l�gica de nulidade e atribuimos as opera��es de 
 		mapeamento e filtragem.
*/
		
// __________________________________________________________________ //

/*  
  	IMPLEMENTANDO OPTIONAL 
 
 	Temos diversas maneiras de criar inst�ncias de Optional.
 	Veremos abaixo alguns exemplos.			
*/		
	System.out.println("1. Conhecendo alguns m�todos Optional:\n");
//	1. Optional.of(value)	
		
		// Criando uma Optional com valor n�o nulo
		Optional<String> OptionalNaoNulo = Optional.of("Hello World");//(null);
		System.out.println("Optional n�o nulo: " + OptionalNaoNulo);
		
		/*	Quando usamos o m�todo 'of()' n�o podemos adicionar um 
		   	valor nulo.
		   	Caso executar com o valor nulo, teremos o erro
		   	NullPointerException.
		*/
		
//	2. Optional.ofNullable(value)
		
		// Criando uma Optional que aceite valores nulos
		Optional<String> OptionalSimNulo = Optional.ofNullable(null);//("Hello World!");
		System.out.println("Optional com nulo: " + OptionalSimNulo);
		
		/*	Quando usamos o m�todo 'ofNullAble(value)' podemos 
		 	trabalhar com valores nulos ou vazios. 
		*/
		
//	3. Optional.empty()
		
		//Criando um Optional vazio
		Optional<String> OptionalVazio = Optional.empty();//("Hello World!");//(null);
		System.out.println("Optional com vazio: " + OptionalVazio);
		
		/*	Quando usamos o m�todo 'empty()', n�o podemos utilizar 
		  	valores nulos, nem retornar um valor como no primeiro 
		  	exemplo.  
		*/
		
// __________________________________________________________________ //
		
		System.out.println("\n2. Praticando com IF/ELSE:\n");
/*	
  	PRATICANDO COM OUTROS EXEMPLOS 
 
 	Vamos escrever mais alguns exemplos para praticar.
 	O exemplo abaixo ter� um tratamento, onde utilizmos a estrutura
 	IF/ELSE para definir se h� algum valor presente.
*/		
		Optional<String> optional = Optional.of("Hello World!");
		
		// Verificando se h� valor presente
		if (optional.isPresent()) {
			System.out.println("O valor presente �: " + optional.get());
		} else {
			System.out.println("O valor est� ausente!");
		}
		
/*
*  	Neste exemplo, vamos usar uma fun��o que chamamos de express�o 
  	Lambda. O que �?
  	
*	� uma fun��o an�nima ou sem declara��o (n�o precisa de nome, 
 	tipo de retorno e modificador de acesso). A sua sintaxe � 
 	simples:
 		
 		(ARGUMENTO) -> (CORPO)
 		
 	Em nosso exemplo baixo, temos:
 	
 		(ARGUMENTO) -> (          CORPO         )
 		   value    ->  System.out.println("...")
  	
  	Essa fun��o pode ser passada como argumento para um m�todo ou 
  	armazenada em vari�veis.
*/
		
		// Usando ifPresent para executar uma a��o se o valor estiver presente
		optional.ifPresent(value -> System.out.println("Valor utilizando ifPresent com Lambda: " + value));
		
/*	
 	Agora veremos outro exemplo, mas dessa vez trabalharemos com 
 	os m�todos 'orElse' e 'orElseGet'.
*/		
		System.out.println("\n2.1. Trabalhando com 'orElse()' e 'orElseGet()\n");
		
		// Criando Optional of() e empty()
		Optional<String> optionalComValor = Optional.of("Hello World!");
		Optional<String> optionalSemValor = Optional.empty();
		
		// Usando orElse para fornecer um valor padr�o
		String valor1 = optionalComValor.orElse("Sem Valor");
		String valor2 = optionalSemValor.orElse("Sem Valor");
		
		System.out.println("Valor com orElse: " + valor1);
		System.out.println("Valor com orElse: " + valor2);
		
		// Usando orElse para fornecer um valor padr�o atrav�s de um Supplier
		String valor3 = optionalComValor.orElseGet(() -> "Sem valor gerado");
		String valor4 = optionalSemValor.orElseGet(() -> "Sem valor gerado");
		
		System.out.println("Valor com orElseGet: " + valor3);
		System.out.println("Valor com orElseGet: " + valor4);
		
		//Criando uma excess�o com orElseThrow para valores n�o presente
		System.out.println("\n2.2. Criando uma excess�o com orElseThrow:\n");
		
		try {
			String valor = optionalSemValor.orElseThrow(() -> new IllegalStateException("Valor Ausente"));
			System.out.println("Valor: " + valor);
		} catch (IllegalStateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
}

/*	REFER�NCIAS
  
 * 	Para que serve o Optional do Java 8? Como Usar?
  	https://pt.stackoverflow.com/questions/447672/para-que-serve-o-optional-do-java-8-como-usar
 
 *	Optional
 	https://github.com/Leon4rdoalves/CookBook-Java/blob/main/16.md
 
 *	Como usar fun��es Lambda em Java
 	https://www.devmedia.com.br/como-usar-funcoes-lambda-em-java/32826
  
 *	Optional no Java 8 e no Java 9
  	https://medium.com/@racc.costa/optional-no-java-8-e-no-java-9-7c52c4b797f1
 * */
 