package Operadores;

import java.util.Scanner;

public class Aritmeticos {

	public static void main(String[] args) {
		
		//Declarando as vari�veis
		int a, b, c, d;
		int e = -100; 
		
		//Declarando a classe Scanner
		Scanner conta = new Scanner(System.in);
		
		//Selecionando os n�meros
		System.out.println("Adicione o valor A: ");
		a = conta.nextInt();
		
		System.out.println("\nAdicione o valor B: ");
		b = conta.nextInt();
		
		System.out.println("\nAdicione o valor C: ");
		c = conta.nextInt();
		
		System.out.println("\nAdicione o valor D: ");
		d = conta.nextInt();
		
		//Trabalhando com os n�meros
		System.out.println("Soma a + b: " + (a + b));
		System.out.println("\nSoma com n�mero negativo b + (-100)): " + (b + e));
		System.out.println("\nSubtra��o (c - d): " + (c - d));
		System.out.println("\nMultiplica��o (a * b): " + (a * b));
		System.out.println("\nDivis�o (c / d): " + (c / d));
		System.out.println("\nM�dulo (resto da divis�o): " + (c % 2));
		System.out.println("\nM�dulo (resto da divis�o): " + (d % 2));
		
	}
	
}
