package correcao;

import java.util.Scanner;

public class natan {
	
	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
/* */
		// Exemplo da Aula
		
		
		System.out.println("Digite um n�mero inteiro:  ");
		int numInt = leia.nextInt();
		
		System.out.println("N�meros com pontos flutuantes:  ");
		double numDouble = leia.nextDouble();
		
		System.out.print("Solta o verbo: "); // palavra //
		String word = leia.next();
		
		System.out.println("Solta a voz ae: "); // linha de texto //
		leia.nextLine(); // Consumir a nova linha restante //
		
		String line = leia.nextLine( );
		
		System.out.println("Numero inteiro: " + numInt );	
		System.out.println("N�mero ponto flutuante: " + numDouble );
		System.out.println("Soltamento do verbo: " + word);
		System.out.println("Soltamento da voz: " + line );

		leia.close( );
		 
	


		//Primeiro Exerc�cio


/*
		System.out.println("Digite um n�mero inteiro:  ");
		int numInt = leia.nextInt( );

		System.out.println("N�meros com pontos flutuantes:  ");
		double numDouble = leia.nextDouble( );

		System.out.print("Solta o verbo: "); // palavra //
		String word = leia.next();

		System.out.print("Solta a voz ae: "); // linha de texto //
		leia.nextLine( ); // Consumir a nova linha restante //
		String line = leia.nextLine( );

		System.out.println("Numero inteiro: " + numInt );
		System.out.println("N�mero ponto flutuante: " + numDouble );
		System.out.println("Soltamento do verbo: " + word);
		System.out.println("Soltamento da voz: " + line );

		leia.close( );
*/
		

/*
		//Primeiro Exerc�cio

		
		System.out.println("Digite um n�mero:  ");
		int numInt = leia.nextInt( );
		
		System.out.println("Digite outro n�mero:  ");
		int numInt2 = leia.nextInt( );
        
        int soma = numInt + numInt2;   
        System.out.println("O resultado da soma de " + numInt+ " + " + numInt2 + " �: " + soma);

        leia.close( );

*/

/*
		// Segundo Exemplo
		System.out.println("Valor do produto: ");
		int numInt = leia.nextInt( );
		
		System.out.println("Valor pago: ");
		int numInt2 = leia.nextInt( );
        
        int sub = numInt - numInt2;   
        System.out.println("O seu troco � de: " + sub);

        leia.close( );

*/


/*
		System.out.println("Qual � o metal mais usado na fabrica��o de latas de refrigerante? ");
		System.out.println("Op��es: Alum�nio, Cobre ou Ferro");
		String word = leia.next ( );

		String resposta = "Alum�nio";
		System.out.println("A sua resposta �: " + word);
		System.out.println("A op��o correta �: " + resposta);

		leia.close( );
*/
		
		
		
		
	}
}

