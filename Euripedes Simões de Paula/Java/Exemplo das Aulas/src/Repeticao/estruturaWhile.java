package Repeticao;

import java.util.Scanner;

public class estruturaWhile {

	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
/*		Primeiro Exemplo */
		
		int numero = -1;
		
		while (numero != 10) {
			
			System.out.println("\n\nDigite um n�mero: ");
			numero = leia.nextInt();
			
			if (numero == 10) {
				System.out.println("\n\nVoc� Acertou!!!"
						+ "\nO n�mero � " + numero);
			}			
			else {
				System.out.println("\n\nVoc� errou..."
						+ "\nO n�mero n�o � " + numero);
			}
			
		}
	
		
/*		Segundo Exemplo
		
		int numero, resultado, contador = 1;
		
		while(contador < 4) {
			System.out.println("\n\nDigite o " 
								+ contador + "� n�mero: ");
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
			contagem--; //n�o erre no operador, ou a contagem ser� incrementada
		}while (contagem > 0);
*/		
		
	}
	
}
