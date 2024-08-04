package Operadores;

public class Notas {

	public static void main(String[] args) {
		
		/* Modelo da equação utilizado para as notas
		 
		  (E + T + Pa) / 3 x 0,4 + P x 0,6	
		  
		 */
 
		//  6 ->  	int E = 10, T = 8, Pa = 8, P = 5;
		
		//  7 ->	int E = 10, T = 10, Pa = 10, P = 5; 
		
		//  8 ->	int E = 10, T = 8, Pa = 8, P = 8;
		
		//  9 -> 	int E = 10, T = 10, Pa = 8, P = 9;
		
		// 10 ->	int E = 10, T = 10, Pa = 10, P = 10;
		
		// Modelo para adicionar as notas
		int E = 0, T = 0, Pa = 0, P = 0;
		
		System.out.printf(
				
				// Equação para média das notas usado na a Sequencial
				"Média Final: " + ( (  (E + T + Pa) / 3  * 0.4 ) + (P * 0.6) )
				);
		
	}
	
}
