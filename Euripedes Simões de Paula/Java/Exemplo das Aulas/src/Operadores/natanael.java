package Operadores;

import java.util.Scanner;

public class natanael {


	public static void main (String[ ] args) { 


			Scanner leia = new Scanner(System.in);

			String player1;
			String player2;
			
			System.out.println("Digite um personagem:  ");
			player1 = leia.nextLine();


			System.out.println("Digite outro personagem: ");  
			player2 = leia.nextLine( );


			System.out.println("Qual é o nível de poder do " + player1);
			int poder1 = leia.nextInt();


			System.out.println("Qual é o nível de poder do " + player2);
			int poder2 = leia.nextInt();

			System.out.println("\n\nÉ hora do Duelo!!!\n\n");

			if (poder1 > poder2){
				System.out.println("Em uma disputa de poder, quem ganha o duelo é " + player1 );
			}else if (poder2 > poder1){
				System.out.println("Em uma disputa de poder, quem ganha o duelo é " + player2 );
			}else if (poder1 == poder2) {
				System.out.println("Em uma disputa de poder, o duelo terminou empatado entre " + player1 + " e " + player2);
			}
			

			leia.close();
	}
}