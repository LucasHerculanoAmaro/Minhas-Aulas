package correcao;

import java.util.Scanner;

public class natan {
	
	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
/* */
		// Exemplo da Aula
		
		
		System.out.println("Digite um número inteiro:  ");
		int numInt = leia.nextInt();
		
		System.out.println("Números com pontos flutuantes:  ");
		double numDouble = leia.nextDouble();
		
		System.out.print("Solta o verbo: "); // palavra //
		String word = leia.next();
		
		System.out.println("Solta a voz ae: "); // linha de texto //
		leia.nextLine(); // Consumir a nova linha restante //
		
		String line = leia.nextLine( );
		
		System.out.println("Numero inteiro: " + numInt );	
		System.out.println("Número ponto flutuante: " + numDouble );
		System.out.println("Soltamento do verbo: " + word);
		System.out.println("Soltamento da voz: " + line );

		leia.close( );
		 
	


		//Primeiro Exercício


/*
		System.out.println("Digite um número inteiro:  ");
		int numInt = leia.nextInt( );

		System.out.println("Números com pontos flutuantes:  ");
		double numDouble = leia.nextDouble( );

		System.out.print("Solta o verbo: "); // palavra //
		String word = leia.next();

		System.out.print("Solta a voz ae: "); // linha de texto //
		leia.nextLine( ); // Consumir a nova linha restante //
		String line = leia.nextLine( );

		System.out.println("Numero inteiro: " + numInt );
		System.out.println("Número ponto flutuante: " + numDouble );
		System.out.println("Soltamento do verbo: " + word);
		System.out.println("Soltamento da voz: " + line );

		leia.close( );
*/
		

/*
		//Primeiro Exercício

		
		System.out.println("Digite um número:  ");
		int numInt = leia.nextInt( );
		
		System.out.println("Digite outro número:  ");
		int numInt2 = leia.nextInt( );
        
        int soma = numInt + numInt2;   
        System.out.println("O resultado da soma de " + numInt+ " + " + numInt2 + " é: " + soma);

        leia.close( );

*/

/*
		// Segundo Exemplo
		System.out.println("Valor do produto: ");
		int numInt = leia.nextInt( );
		
		System.out.println("Valor pago: ");
		int numInt2 = leia.nextInt( );
        
        int sub = numInt - numInt2;   
        System.out.println("O seu troco é de: " + sub);

        leia.close( );

*/


/*
		System.out.println("Qual é o metal mais usado na fabricação de latas de refrigerante? ");
		System.out.println("Opções: Alumínio, Cobre ou Ferro");
		String word = leia.next ( );

		String resposta = "Alumínio";
		System.out.println("A sua resposta é: " + word);
		System.out.println("A opção correta é: " + resposta);

		leia.close( );
*/
		
		
		
		
	}
}

