package Operadores;

public class Notas {

	public static void main(String[] args) {
		
		/*	(E + T + Pa) / 3 x 0,4 + P x 0,6	*/
 
		//  6 ->  	int E = 10, T = 8, Pa = 8, P = 5;
		
		//  7 ->	int E = 10, T = 10, Pa = 10, P = 5; 
		
		//  8 ->	int E = 10, T = 8, Pa = 8, P = 8;
		
		//  9 -> 	int E = 10, T = 10, Pa = 8, P = 9;
		
		// 10 ->	int E = 10, T = 10, Pa = 10, P = 10;
		
		int E = 0, T = 0, Pa = 0, P = 0;
		
		System.out.printf(
				"Média Final: " + ( (  (E + T + Pa) / 3  * 0.4 ) + (P * 0.6) )
				);
		
	}
	
}
