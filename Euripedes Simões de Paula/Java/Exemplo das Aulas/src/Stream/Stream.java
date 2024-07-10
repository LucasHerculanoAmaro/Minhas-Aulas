package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stream {

	public static void main(String[] args) {
		
/*	INTRODU��O 
 
 * 	Esta API foi introduzida no Java 8, e faz parte do pacote 
	'java.util.stream'.
	
 *	Quase todas as aplica��es Java criam e processam cole��es,
 	onde agrupam e processam dados. Esses dados podem ser
 	consultados e alterados de maneira similar ao que j� 
 	estudamos em SQL.
	
 *	A STREAM � um recurso muito utilizado, que oferece efici�ncia 
 	no processamento de dados (cole��es) de forma declarativa, 
 	tornando mais simples, proporcionando um c�digo limpo, com 
 	f�cil manuten��o e evitar erros.
	
 *	Ao processar os dados, � poss�vel criar combina��es nas 
 	opera��es, onde s�o incorporados o Paradigma Funcional e 
 	a Express�o Lambda.
	
	
	PROGRAMA��O COM STREAM
	
 *	Uma boa defini��o para STREAM � uma sequencia de elementos de 
 	uma fonte de dados que suportam opera��es de agrega��o, e que 
 	s�o divididos por:
 	
 	-> 	Sequencia de elementos: A STREAM proporciona uma 
 	 	interface para um conjunto de valores sequenciais de um 
 	 	tipo de elemento particular.
 	 	
 	-> 	Fonte de dados: As STREAM trabalham com elementos
 	 	provenientes de uma fonte de dados, como: collections,
 	 	matrizes ou recursos de Entrada e Sa�da.
 	 	
 	-> 	Opera��es de agrega��o: As STREAM suportam opera��es 
 	 	do tipo SQL e opera��es comuns de outras linguagens 
 	 	funcionais, como: filter, map, reduce, find, match e 
 	 	sorted... 
	
*	Falando sobre a diferen�a entre a STREAM e a COLLECTIONS, 
	encontramos certas similaridades j� que ambas as interfaces 
	trabalham com sequencia de dados.
	
*	A diferen�a entre elas � que, a COLLECTION referem-se a dados
	enquanto a STREAM refere-se aos resultados encontrados em uma 
	opera��o aplicada na COLLECTION.

*	H� duas caracter�sticas que diferenciam as STREAM das 
	COLLECTIONS:
	
	-> 	Estrutura de processo: Lembrando que a tradu��o para 
		'stream' � 'fluxo'. Entendendo isso, podemos definir 
		que uma STREAM retorna um fluxo, onde � poss�vel encadear 
		opera��es para formar um processo mais abrangente.
		
	->	Itera��o interna: As COLLECTIONS trabalham com itera��es 
		explicitas, j� as STREAMS realizam itera��o internas,
		nos bastidores.
		
*	E qual � o papel dos demais m�todos atribuidos a 'stream()'?
	Vamos criar um exeplo pr�tico utiizando os m�todos associados
	a 'stream()'.		
*/
	System.out.println(
			  "********************************\n"
			+ "* Entendendo os m�todos STREAM *\n"
			+ "********************************");
	
//	Criando uma lista com 5 nomes
	List<String> nomes = Arrays.asList("Lucas", "Juliana", "Majjin", "Leia", "Nelson");

//	Iniciando uma List para filtrar os nomes
	List<String> filtroNomes = nomes.stream()
			
	/****************************************************************
	 *							 FILTER								*
	 *	Est� opera��o uma opera��o intermedi�ria que permite 		*
	 *	selecionar elementos de uma stream que corresponda a uma 	*
	 *	fun��o que retorna um valor booleano.						*
	 *																*
	 *	Ela retorna uma nova stream contendo os elementos que 		*
	 *	satisfazem os filtros. 										*
	 *																*
	 *	Aqui utilizamos um 'filter()' para selecionar apenas os 	*
	 *	nomes que iniciam com 'L'.									*
	 ****************************************************************/
			
		.filter(nome -> nome.startsWith("L"))
		.collect(Collectors.toList());
	System.out.println(filtroNomes);

	/****************************************************************
	 *							 SORTED								*
	 *	Esta � uma opera��o intermedi�ria que retorna uma stream	*
	 *	com os elementos ordenados.									*
	 *																*
	 *	� importante conhecer as varia��es de 'sorted', que s�o:	*
	 *																*
	 *	->	Sem Parametro: Os elementos s�o ordenados de forma		* 
	 *		natural.												*
	 *	->	Com 'Comparator': Os elemetos s�o ordenados com um		*
	 *		'Comparator' implementado.								*
	 *																*
	 ****************************************************************/	
	
	
/*
	
	->	FILTER: � uma opera��o intermedi�ria que permite selecionar 
		elementos de uma stream que corresponda a uma fun��o que 
		retorna um valor booleano.
		Ela retorna uma nova stream contendo os elementos que 
		satisfazem os filtros.
		
	->	SORTED:
	->	MAP:
	->	COLLECT:

*/
		
		
	}
	
}

/*	REFER�NCIAS

 * 	Stream
	https://github.com/Leon4rdoalves/CookBook-Java/blob/main/17.md
	
 * 	Processamento de dados com strea do Java SE 8 - Perte 1
	https://www.oracle.com/br/technical-resources/articles/java/processing-streams-java-se-8.html

 * 

 *  

 *
  
*/
