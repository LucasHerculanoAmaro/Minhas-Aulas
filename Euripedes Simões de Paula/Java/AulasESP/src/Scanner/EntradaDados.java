package Scanner;

import java.util.Scanner;

public class EntradaDados {

	
	public static void main(String[] args) {
		
		// Segundo passo: Crie um objeto Scanner para ler a entrada
		Scanner leia = new Scanner(System.in);

		// Terceiro Passo: crie um método para ler os dados
		System.out.print("Informe um número inteiro: ");
		int valorInt = leia.nextInt();
		
		System.out.print("Informe um número float: ");
		float valorFloat = leia.nextFloat();

		leia.nextLine(); // Consumindo a nova linha
		/*Não utilizar o leia.nextLine(); pode fazer o programa registrar os dados acima do buffer de entrada da próxima instrução*/

		System.out.print("Informe um número string: ");
		String valorString = leia.nextLine();

		// Quarto Passo: Fechando o scanner
		leia.close();

		// Quinto Passo: Exibindo os valores lidos
		System.out.print("Número inteiro: " + valorInt);
		System.out.print("Número float: " + valorFloat);
		System.out.print("String: " + valorString);

	}

}
