package Repeticao;

import java.util.Scanner;

public class estruturaWhile {

	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
/*		Primeiro Exemplo */
		
		int numero = -1;
		
		while (numero != 10) {
			
			System.out.println("\n\nDigite um número: ");
			numero = leia.nextInt();
			
			if (numero == 10) {
				System.out.println("\n\nVocê Acertou!!!"
						+ "\nO número é " + numero);
			}			
			else {
				System.out.println("\n\nVocê errou..."
						+ "\nO número não é " + numero);
			}
			
		}
	
		
/*		Segundo Exemplo
		
		int numero, resultado, contador = 1;
		
		while(contador < 4) {
			System.out.println("\n\nDigite o " 
								+ contador + "º número: ");
			numero = leia.nextInt();
			
			resultado = numero * 3;
			System.out.println(numero + " x 3 = " + resultado);
			System.out.println(
					"----------------------------------------------------------");
			contador++;
		}	
		
		System.out.println("\t\t O Programa foi encerrado");
*/
		
/*		Terceiro Exemplo 
		
		int i = 0;
		
		do {
			System.out.println(i);
			i++;
		
		}while(i < 4);
*/
		
/*		Quarto Exemplo 
		int contagem = 10;
		
		System.out.println(	  "########################"
							+ "# Contagem regressiva! #"
							+ "########################");
		do {
			System.out.println(contagem);
			contagem--; //não erre no operador, ou a contagem será incrementada
		}while (contagem > 0);
*/		
		
	}
	
}
