package MatrizesVetores;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Vetores {

	public static void main(String[] args) {

		
/*		Primeiro Exemplo */
		// Declarando e inicializando um vetor de números inteiros
		int numeros[] = {1, 2, 3, 4, 5};
	
		// Acessando elementos do vetor
		System.out.println("Imprimindo o primeiro valor do vetor: " + numeros[0]);
		System.out.println("Imprimindo o quarto valor do vetor: " + numeros[4]);
		System.out.println("Imprimindo o segundo valor do vetor: " + numeros[1]);
		
		System.out.println("\n####################################################\n");
		//Interando sobre o vetor
		for (int i = 0; i < numeros.length; i++) {
			System.out.println("Contei " + numeros[i]);
		}
		
		System.out.println("\n####################################################\n");
		
		//Pegando o tamanho do array
		System.out.println("Array do tamanho de " + numeros.length);

		
/*		Segundo Exemplo*/ 	
		String petA[] = {"Gato", "Cachorro", "Jabuti"};
		String petB[] = {"Pato", "Galinha", "Porco"};
		
		int listaA[] = {1, 2, 3};
		int listaB[] = {1, 2, 3};
		
		System.out.println("Veja se as listas são iguais:\n");
		
		// Comparando listas diferentes
		if(!Arrays.equals(petA, petB)) {
			System.out.println("A lista de pets A é igual a lista de pets B.");
		}else {
		System.out.println("A lista de pets A não é igual a lista de pets B.");
		}

		// Comparando listas iguais
		if(!Arrays.equals(listaA, listaB)) {
			System.out.println("A lista A é igual a lista B.");
		}else {
			System.out.println("A lista A não é igual a lista B.");
		}

	}	
}
