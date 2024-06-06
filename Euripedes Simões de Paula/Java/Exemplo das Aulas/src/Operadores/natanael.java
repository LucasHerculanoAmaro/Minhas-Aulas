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


			System.out.println("Qual é o poder do " + line);
			int line = leia.nextInt();


			System.out.println("Qual é o poder do " + line2);
			int line2 = leia.nextInt();


// System.out.println(“HORA DO DUELO”);
// Int duel = line > line2;


			if (leia.nextInt()){
				Int duel = line > line2;
				System.out.println("Em uma disputa de poder, quem ganha o duelo é " + duel );
			}
			if (leia.nextInt()){
				int duel = line < line2;
				System.out.println("Em uma disputa de poder, quem ganha o duelo é " + duel );
			}


/*
If (leia.hasNextInt()){
      Int duel = line > line2;
      System.out.println(“Em uma disputa de poder, quem ganha o duelo é“ + duel );
}
Of (leia.hasNextInt()){
       Int duel = line < line2;
       System.out.println(“Em uma disputa de poder, quem ganha o duelo é“ + duel );
*/
			leia.close();
	}
}