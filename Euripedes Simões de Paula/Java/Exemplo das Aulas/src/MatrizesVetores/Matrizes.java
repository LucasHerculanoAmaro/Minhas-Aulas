package MatrizesVetores;

public class Matrizes {
	
	public static void main(String[] args) {
		
		// Criando os Vetores
		int[] vetorA = {1, 2, 3};
		int[] vetorB = {4, 5, 6};
		int[] vetorC = new int[3];

		// Somando os Vetores
		for (int i = 0; i < vetorA.length; i++) {
			vetorC[i] = vetorA[i] + vetorB[i];
		}
		
		// Imprimindo a soma dos vetores
		for (int num : vetorC) {
			System.out.println(num + " ");
		}
		System.out.println();
		
		
	}
	
}