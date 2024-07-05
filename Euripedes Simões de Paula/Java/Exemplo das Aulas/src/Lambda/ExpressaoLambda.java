package Lambda;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ExpressaoLambda {

	public static void main(String[] args) {
/*		EXPPRESS�O LAMBDA
 	
* 		Essa express�o � uma fun��o an�nima que h� no Java.
 		Esta fun��o n�o tem nome e pode ser passada por argumento
 		para m�todos ou armazenada em vari�veis.
 	
* 		Essa express�o surgiu na vers�o Java 8, e muitos 
 		profissionais utilizam essa fun��o para simplificar o 
 		c�digo que utiliza interfaces com m�todos abstratos.
 	
* 		A sua sintaxe � simples, e apresenta:
 						( PARAMETROS ) -> EXPRESS�O
 								OU
 						( PARAMETROS ) -> ( DECLARA��O; )
 
*		Veja a seguir alguns exemplos da express�o Lambda.

  		
  		CURIOSIDADE
   	
*   	RUNNABLE = � uma interface que pode ser implementada por
   		qualquer classe capaz de definir e executar por uma THREAD.
   		A classe pode definir um m�todo sem argumento, chamado 
   		'run()'.
   	
*   	THREAD = � uma forma de excutar um processo em paralelo a
   		ou processo em execu��o.
   	
*/		
		System.out.println("Criando um 'Hello World' com Lambda sem Par�metro:");
//		Criando 'Hello World' com Runnable + Lambda
		Runnable r = () -> System.out.println("Hello World!");
		
// 		Executando o nosso Obeto com o m�todo 'run()'
		r.run(/* m�todo sem argumento */);
		
		
		System.out.println("\nCriando um 'Ol�' com Lambda com Par�metro:");

/*		INTERFACE FUNCIONAL
 		
* 		Nos exemplos a seguir ser� ulilizado as Interfaces Funcionais,
 		onde apenas um m�todo abstrato ser� criado.
 
* 		As anota��es n�o s�o obrigat�rias.
 		
*/		
		
//		Criando uma interface 'Saudacao', para instanciar um m�todo
		@FunctionalInterface
		interface Saudacao {

//			Criando o m�todo 'digaOi' com 'nome' do tipo String			
			void digaOla(String name);
		}
		
//		Atribuindo o objeto ao par�metro da interface
		Saudacao saudacao = (name) -> System.out.println("Ol�, " + name);
		
//		Adicionando o valor no par�metro que ser� atribuido ao objeto
		saudacao.digaOla("Lucas");
	
		System.out.println("\nCriando opera��es matem�ticas com Lambda: ");
		
//		Criando uma interface 'OperacaoMatematica' com dois par�metros
		@FunctionalInterface
		interface OperacaoMatematica{
			int operacao(int a, int b);
		}
		
//		Realizando as opera��es
		OperacaoMatematica adicao = (a, b) -> a + b;
		OperacaoMatematica subtracao = (a, b) -> a - b;
		
// 		Imprimindo a soma das opera��es
		System.out.println("Adi��o: " + adicao.operacao(5, 3));
		System.out.println("Subtra��o: " + subtracao.operacao(5, 3));
		
		System.out.println("\nCriando uma lista e selecionando os nomes com Lambda: ");
		
//		Criando uma lista de nomes
		List<String> nomes = Arrays.asList("Lucas", "Juliana", "Majin Boo", "Princesa Leia", "Nelson");
		
//		Utilizando forEach com Lambda para imprimir
		nomes.forEach(nome -> System.out.println(nome));
		
	}
	
}

/*		REFER�NCIAS
 
* 		Runnable (Java Platform SE 8)
 		https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
 
* 		Java Virtual Thread: Conceitos e quando (ou n�o) usar
 		https://medium.com/@boschtechbr/java-virtual-thread-conceito-e-quando-ou-n%C3%A3o-usar-7c56238e951d#:~:text=Antes%20de%20mais%20nada%2C%20o,sistema%20operacional%E2%80%9D%20%5B1%5D.
 
 
 
 * 
 * */
