package Lambda;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ExpressaoLambda {

	public static void main(String[] args) {
/*		EXPPRESSÃO LAMBDA
 	
* 		Essa expressão é uma função anônima que há no Java.
 		Esta função não tem nome e pode ser passada por argumento
 		para métodos ou armazenada em variáveis.
 	
* 		Essa expressão surgiu na versão Java 8, e muitos 
 		profissionais utilizam essa função para simplificar o 
 		código que utiliza interfaces com métodos abstratos.
 	
* 		A sua sintaxe é simples, e apresenta:
 						( PARAMETROS ) -> EXPRESSÃO
 								OU
 						( PARAMETROS ) -> ( DECLARAÇÃO; )
 
*		Veja a seguir alguns exemplos da expressão Lambda.

  		
  		CURIOSIDADE
   	
*   	RUNNABLE = é uma interface que pode ser implementada por
   		qualquer classe capaz de definir e executar por uma THREAD.
   		A classe pode definir um método sem argumento, chamado 
   		'run()'.
   	
*   	THREAD = É uma forma de excutar um processo em paralelo a
   		ou processo em execução.
   	
*/		
		System.out.println("Criando um 'Hello World' com Lambda sem Parâmetro:");
//		Criando 'Hello World' com Runnable + Lambda
		Runnable r = () -> System.out.println("Hello World!");
		
// 		Executando o nosso Obeto com o método 'run()'
		r.run(/* método sem argumento */);
		
		
		System.out.println("\nCriando um 'Olá' com Lambda com Parâmetro:");

/*		INTERFACE FUNCIONAL
 		
* 		Nos exemplos a seguir será ulilizado as Interfaces Funcionais,
 		onde apenas um método abstrato será criado.
 
* 		As anotações não são obrigatórias.
 		
*/		
		
//		Criando uma interface 'Saudacao', para instanciar um método
		@FunctionalInterface
		interface Saudacao {

//			Criando o método 'digaOi' com 'nome' do tipo String			
			void digaOla(String name);
		}
		
//		Atribuindo o objeto ao parâmetro da interface
		Saudacao saudacao = (name) -> System.out.println("Olá, " + name);
		
//		Adicionando o valor no parâmetro que será atribuido ao objeto
		saudacao.digaOla("Lucas");
	
		System.out.println("\nCriando operações matemáticas com Lambda: ");
		
//		Criando uma interface 'OperacaoMatematica' com dois parâmetros
		@FunctionalInterface
		interface OperacaoMatematica{
			int operacao(int a, int b);
		}
		
//		Realizando as operações
		OperacaoMatematica adicao = (a, b) -> a + b;
		OperacaoMatematica subtracao = (a, b) -> a - b;
		
// 		Imprimindo a soma das operações
		System.out.println("Adição: " + adicao.operacao(5, 3));
		System.out.println("Subtração: " + subtracao.operacao(5, 3));
		
		System.out.println("\nCriando uma lista e selecionando os nomes com Lambda: ");
		
//		Criando uma lista de nomes
		List<String> nomes = Arrays.asList("Lucas", "Juliana", "Majin Boo", "Princesa Leia", "Nelson");
		
//		Utilizando forEach com Lambda para imprimir
		nomes.forEach(nome -> System.out.println(nome));
		
	}
	
}

/*		REFERÊNCIAS
 
* 		Runnable (Java Platform SE 8)
 		https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
 
* 		Java Virtual Thread: Conceitos e quando (ou não) usar
 		https://medium.com/@boschtechbr/java-virtual-thread-conceito-e-quando-ou-n%C3%A3o-usar-7c56238e951d#:~:text=Antes%20de%20mais%20nada%2C%20o,sistema%20operacional%E2%80%9D%20%5B1%5D.
 
 
 
 * 
 * */
