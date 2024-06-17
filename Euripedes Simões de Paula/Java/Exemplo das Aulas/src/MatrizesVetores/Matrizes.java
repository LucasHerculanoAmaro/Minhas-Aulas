package MatrizesVetores;

public class Matrizes {

	public static void main(String[] args) {
		
		// Vetores
		int[] vetorA = {1, 2, 3};
		int[] vetorB = {4, 5, 6};
		int[] vetorC = new int[3]; // variável para atribuir a soma
		
		// Somando os vetores
		for (int i = 0; i < vetorA.length; i++) {
			vetorC[i] = vetorA[i] + vetorB[i];
		}
		
		// Imprimindo soma
		System.out.println("Soma dos vetores: ");
		for (int num : vetorC) {
			System.out.println( num + " ");
		}
		
		System.out.println("##############");

		// Matrizes
		int[][] matrizA = {
				{1, 2, 3},
				{4, 5, 6}
		};
		int[][] matrizB = {
				{7, 8, 9},
				{10, 11, 12}
		};
		int[][] matrizC = new int [2][3];// Para atribuir os valores da soma
		
		//Soma de Matrizes
		for (int i = 0; i < matrizA.length; i++) {
			for (int j = 0; j < matrizA[i].length; j++) {
				matrizC[i][j] = matrizA[i][j] + matrizB[i][j];
			}
		}
		
		System.out.println("Somando as matrizes: ");
		
		for (int i = 0; i < matrizC.length; i++) {
			for (int j = 0; j < matrizC[i].length; j++) {
				System.out.println(matrizC[i][j] + " ");
			}
			System.out.println(" ");
		}
		
	}
	
}
