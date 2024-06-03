package Operadores;

import java.util.Scanner;

public class Aritmeticos {

	public static void main(String[] args) {
		
		//Declarando as variáveis
		int a, b, c, d;
		int e = -100; 
		
		//Declarando a classe Scanner
		Scanner conta = new Scanner(System.in);
		
		//Selecionando os números
		System.out.println("Adicione o valor A: ");
		a = conta.nextInt();
		
		System.out.println("\nAdicione o valor B: ");
		b = conta.nextInt();
		
		System.out.println("\nAdicione o valor C: ");
		c = conta.nextInt();
		
		System.out.println("\nAdicione o valor D: ");
		d = conta.nextInt();
		
		//Trabalhando com os números
		System.out.println("Soma a + b: " + (a + b));
		System.out.println("\nSoma com número negativo b + (-100)): " + (b + e));
		System.out.println("\nSubtração (c - d): " + (c - d));
		System.out.println("\nMultiplicação (a * b): " + (a * b));
		System.out.println("\nDivisão (c / d): " + (c / d));
		System.out.println("\nMódulo (resto da divisão): " + (c % 2));
		System.out.println("\nMódulo (resto da divisão): " + (d % 2));
		
	}
	
}
