package Repeticao;

import java.util.Scanner;

public class estruturaWhile {

	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
		
		int numero = -1;
		while (numero != 10) {
			
			System.out.println("Digite um número: ");
			numero = leia.nextInt();
			
			leia.close();
			
			if (numero == 10) {
				System.out.println("\nVocê Acertou!!!");
			}			
			else {
				System.out.println("Você errou...");
			}
			
		}
		
	}
	
}
