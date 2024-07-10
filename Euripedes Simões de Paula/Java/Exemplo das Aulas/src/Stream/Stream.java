package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stream {

	public static void main(String[] args) {
		
/*	INTRODUÇÃO 
 
 * 	Esta API foi introduzida no Java 8, e faz parte do pacote 
	'java.util.stream'.
	
 *	Quase todas as aplicações Java criam e processam coleções,
 	onde agrupam e processam dados. Esses dados podem ser
 	consultados e alterados de maneira similar ao que já 
 	estudamos em SQL.
	
 *	A STREAM é um recurso muito utilizado, que oferece eficiência 
 	no processamento de dados (coleções) de forma declarativa, 
 	tornando mais simples, proporcionando um código limpo, com 
 	fácil manutenção e evitar erros.
	
 *	Ao processar os dados, é possível criar combinações nas 
 	operações, onde são incorporados o Paradigma Funcional e 
 	a Expressão Lambda.
	
	
	PROGRAMAÇÃO COM STREAM
	
 *	Uma boa definição para STREAM é uma sequencia de elementos de 
 	uma fonte de dados que suportam operações de agregação, e que 
 	são divididos por:
 	
 	-> 	Sequencia de elementos: A STREAM proporciona uma 
 	 	interface para um conjunto de valores sequenciais de um 
 	 	tipo de elemento particular.
 	 	
 	-> 	Fonte de dados: As STREAM trabalham com elementos
 	 	provenientes de uma fonte de dados, como: collections,
 	 	matrizes ou recursos de Entrada e Saída.
 	 	
 	-> 	Operações de agregação: As STREAM suportam operações 
 	 	do tipo SQL e operações comuns de outras linguagens 
 	 	funcionais, como: filter, map, reduce, find, match e 
 	 	sorted... 
	
*	Falando sobre a diferença entre a STREAM e a COLLECTIONS, 
	encontramos certas similaridades já que ambas as interfaces 
	trabalham com sequencia de dados.
	
*	A diferença entre elas é que, a COLLECTION referem-se a dados
	enquanto a STREAM refere-se aos resultados encontrados em uma 
	operação aplicada na COLLECTION.

*	Há duas características que diferenciam as STREAM das 
	COLLECTIONS:
	
	-> 	Estrutura de processo: Lembrando que a tradução para 
		'stream' é 'fluxo'. Entendendo isso, podemos definir 
		que uma STREAM retorna um fluxo, onde é possível encadear 
		operações para formar um processo mais abrangente.
		
	->	Iteração interna: As COLLECTIONS trabalham com iterações 
		explicitas, já as STREAMS realizam iteração internas,
		nos bastidores.
		
*	E qual é o papel dos demais métodos atribuidos a 'stream()'?
	Vamos criar um exeplo prático utiizando os métodos associados
	a 'stream()'.		
*/
	System.out.println(
			  "********************************\n"
			+ "* Entendendo os métodos STREAM *\n"
			+ "********************************");
	
//	Criando uma lista com 5 nomes
	List<String> nomes = Arrays.asList("Lucas", "Juliana", "Majin", "Leia", "Nelson");

			
	/****************************************************************
	 *							 FILTER								*
	 *	Está operação uma operação intermediária que permite 		*
	 *	selecionar elementos de uma stream que corresponda a uma 	*
	 *	função que retorna um valor booleano.						*
	 *																*
	 *	Ela retorna uma nova stream contendo os elementos que 		*
	 *	satisfazem os filtros. 										*
	 *																*
	 *	Aqui utilizamos um 'filter()' para selecionar apenas os 	*
	 *	nomes que iniciam com 'L'.									*
	 ****************************************************************/

	List<String> exemplo1 = nomes.stream()
								.filter(nome -> nome.startsWith("L"))
								.collect(Collectors.toList());
	
	System.out.println("\nRetorno com filter(): " + exemplo1);

	/****************************************************************
	 *							 SORTED								*
	 *	Esta é uma operação intermediária que retorna uma stream	*
	 *	com os elementos ordenados.									*
	 *																*
	 *	É importante conhecer as variações de 'sorted', que são:	*
	 *																*
	 *	->	Sem Parametro: Os elementos são ordenados de forma		* 
	 *		natural.												*
	 *	->	Com 'Comparator': Os elemetos são ordenados com um		*
	 *		'Comparator' implementado.								*
	 *																*
	 *	OBS: Repare que, utilizando o 'soted()', temos o retorno 	*
	 *	de uma lista ordenada										*
	 *																*
	 ****************************************************************/	
	
	List<String> exemplo2 = nomes.stream()
								 .sorted()
								 .collect(Collectors.toList());
	
	System.out.println("\nRetorno com sorted(): " + exemplo2);
	System.out.println("Retorno sem sorted(): " + nomes);
	
	/****************************************************************
	 * 							  MAP								*
	 * 	Esta operação intermediária aplica uma função a cada 		*
	 * 	elemento de stream que transforma os elementos e retorna	*
	 * 	uma nova stream com os elementos transformados.				*
	 * 																*
	 * 	No exemplo abaixo, utilizamos o 'map()' para retornar uma	*
	 * 	nova stream com os elementos transformados, em caixa alta.	*
	 * 																*
	 ****************************************************************/
	
	List<String> exemplo3 = nomes.stream()
								 .map(String::toUpperCase)
								 .collect(Collectors.toList());
	System.out.println("\nRetorno com map(): " + exemplo3);
		
	/****************************************************************
	 * 							 COLLECT							*
	 * 	Esta é uma operação terminal que transforma os elementos	*
	 * 	da stream em uma coleção. 									*
	 * 																*
	 * 	Esta operação é frequentemente usada com o 'Collectors'		*
	 * 	para coletar os elementos da lista, mapas, conjuntos, etc.	*
	 * 																*
	 ****************************************************************/

	List<String> exemplo4 = nomes.stream()
								 .collect(Collectors.toList());
	System.out.println("\nRetorno com collect(): " + exemplo4);

	/****************************************************************	
	 * 	Para finalizar, vamos criar um exemplo, onde utilizamos 	*
	 * 	todos os métodos em uma tarefa.								*
	 * 																* 	
	 * 	Vamos filtrar, ordenar, mapear e coletar os nomes que: 		*
	 * 																* 	
	 * 	->	Começam com a letra 'L'; 								*
	 * 	->	Converter as letras para caixa alta;					*
	 * 	-> 	Coletar os elementos em uma lista.						*
	 * 																* 	
	 ****************************************************************/
	
	List<String> exemplo5 = nomes.stream()
			
//	Filtra a stream para inluir apenas nomes que iniciam com a letra L 			
								.filter(nome -> nome.startsWith("L"))
								
//	Ordena os nomes em ordem alfabética
								.sorted()
								
//	Converte os elementos para maiúsculas
								.map(String::toUpperCase)
								
// 	Coleta os resultados em uma lista
								.collect(Collectors.toList());
	
	System.out.println("\nRetorno com (filter, sorted, map e collect): ");
	exemplo5.forEach(System.out::println);
	
	}
	
}

/*	REFERÊNCIAS

 * 	Stream
	https://github.com/Leon4rdoalves/CookBook-Java/blob/main/17.md
	
 * 	Processamento de dados com strea do Java SE 8 - Perte 1
	https://www.oracle.com/br/technical-resources/articles/java/processing-streams-java-se-8.html

 * 

 *  

 *
  
*/
