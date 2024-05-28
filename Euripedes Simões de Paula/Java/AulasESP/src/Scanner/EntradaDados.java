package Scanner;

import java.util.Scanner;

public class EntradaDados {

	
	public static void main(String[] args) {
		
		// Segundo passo: Crie um objeto Scanner para ler a entrada
		Scanner leia = new Scanner(System.in);

		// Terceiro Passo: crie um m�todo para ler os dados
		System.out.print("Informe um n�mero inteiro: ");
		int valorInt = leia.nextInt();
		
		System.out.print("Informe um n�mero float: ");
		float valorFloat = leia.nextFloat();

		leia.nextLine(); // Consumindo a nova linha
		/*N�o utilizar o leia.nextLine(); pode fazer o programa registrar os dados acima do buffer de entrada da pr�xima instru��o*/

		System.out.print("Informe um n�mero string: ");
		String valorString = leia.nextLine();

		// Quarto Passo: Fechando o scanner
		leia.close();

		// Quinto Passo: Exibindo os valores lidos
		System.out.print("N�mero inteiro: " + valorInt);
		System.out.print("N�mero float: " + valorFloat);
		System.out.print("String: " + valorString);

	}

}
