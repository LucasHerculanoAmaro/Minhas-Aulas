package MatrizesVetores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Matrizes {
	
	public static void main(String[] args) {
	

		int Ml = 1000;

		System.out.println("Você tem 1 litro de sorvete!");
		

 			do {
			
			Ml -= 100;
			System.out.println("Você tomou sorvete demais! Agora restam somente " + Ml + "MLs!");
				
			} while (Ml > 100 );

		        System.out.println(" Você tomou sorvete demais! Seu sorvete acabou! ");

		
	/*	// Criando os Vetores
		int[] vetorA = {1, 2, 3};
		int[] vetorB = {4, 5, 6};
		int[] vetorC = new int[3];

		// Somando os Vetores
		for (int i = 0; i < vetorA.length; i++) {
			vetorC[i] = vetorA[i] + vetorB[i];
		}
		
		// Imprimindo a soma dos vetores
		System.out.println("Soma dos vetores: " );
		for (int num : vetorC) {
			System.out.println(num);
		}
		
		// Matrizes
		int[][] matrizA = {
			{1, 2, 3},
			{4, 5, 6}
		};
		int[][] matrizB = {
				{7, 8, 9},
				{10, 11, 12}
		};
		int[][] matrizC = new int[2][3];
		
		// Soma das matrizes
		for (int linha = 0; linha < matrizA.length; linha++) {
			for (int coluna = 0; coluna < matrizA[linha].length; coluna++) {
				matrizC [linha][coluna] = 
						matrizA[linha][coluna] + matrizB [linha][coluna];
			}
		}
		
		// Imprime a soma das matrizes
		System.out.println("Soma das matrizes: ");
		for (int linha = 0; linha < matrizC.length; linha++ ) {
			for (int coluna = 0; coluna < matrizC[linha].length; coluna++) {
				System.out.println("Posição ["+ linha + "]["+  coluna + "] = " + matrizC[linha][coluna]);
			}
		}
*/
		
/*		Segundo Exemplo
		int[][] matrizA = new int[3][3];
		Scanner leia = new Scanner(System.in);
		
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++ ) {
				System.out.println(
						"Insira o valor para a posição [" + linha + "][" + coluna + "]: ");
				matrizA[linha][coluna] = leia.nextInt();
			}
		}
		
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				System.out.println(
						"O valor na posição [" + linha + "][" + coluna + "] é: "
						+ matrizA[linha][coluna] );
			}
		}
*/		
		
/*		Terceiro Exemplo 
		int soma = 0;
		int vetor[] = new int[4];
		int[][] matrizA = new int[5][4];
		
		Scanner leia = new Scanner(System.in);
		
		// Adicionando os dados na matriz
		for (int linha = 0; linha < 5; linha++) {
			for (int coluna = 0; coluna < 4; coluna++) {
				System.out.println("Insira um valor para a posição ["
						+ linha + "][" + coluna + "]");
				matrizA[linha][coluna] = leia.nextInt();
			}
		}
		
		for (int coluna = 0; coluna < 4; coluna++) {
			for (int linha = 0; linha < 5; linha++) {
				soma += matrizA[linha][coluna];
			}
			
			vetor[coluna] = soma;
			
			soma = 0;
		}
		
		for (int coluna = 0; coluna < 4; coluna++) {
			System.out.println(
					"Somando os elementos " + (coluna + 1) + " é " + vetor[coluna]);
		}
*/		
		
/*		Quarto Exemplo 
		int[] numeros = {1,2,3,4,5};
		
		for (int num : numeros) {
			System.out.println("Imprima: " + num);
		}
*/		
		
/*		Quinto Exemplo
		List<String> frutas = new ArrayList<>();
		
		frutas.add("Maça");
		frutas.add("Banana");
		frutas.add("Laranja");
		
		for (String fruta : frutas) {
			System.out.println(fruta);
		}
*/
		
	}
	
}