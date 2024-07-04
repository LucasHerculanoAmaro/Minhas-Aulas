package Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Collections {

	public static void main(String[] args) {
		
/* ArrayList */
		
		// Criando uma lista
		List<String> list = new ArrayList<>();
		// Adicionando valores a lista
		list.add("A");
		list.add("B");
		list.add("C");
		// Selecionando valores da lista
		System.out.println(list.get(1));
		
/* LinkedList */
		
		// Criando uma LinkedList
		List<String> linkedList = new LinkedList<>();
		// Adicionando valores a LinkedList
		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("C");
		// Selecionando valores da lista
		System.out.println(linkedList.get(2));
		
/* HashSet */
		
		// Criando uma HashSet
		Set<String> set = new HashSet<>();
		// Adicionando valores a HashSet
		set.add("A");
		set.add("B");
		set.add("A"); // duplicata -> não será adicionada
		// Descobrindo o tamanho da HashSet
		System.out.println(set.size());
		
/* HashMap */
		
		// Criando uma HashMap
		Map<String, Integer> map = new HashMap<>();
		// Adicionando valores a HashMap
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		// Selecionando o terceiro elemento da HashMap
		System.out.println(map.get("C"));
		
/* Queue */
		
		// Criando uma Queue
		Queue<String> queue = new LinkedList<>();
		// Adicionando valores a HashMap
		queue.add("Primeiro");
		queue.add("Segundo");
		queue.add("Terceiro");
		// Selecionando os elementos da Queue
		System.out.println("Elementos da Queue: " + queue); 
		// Removendo elementos da Queue
		System.out.println("Removendo elemento [" + queue.poll() + "] da Queue: " );
		// Selecionando Queue depois das remoções
		System.out.println("Queue depois das remoções: " + queue);
		
		
// Exercício: Leia o conteúdo, Escreva os exemplos e Explique com suas palavras o código: https://www.devmedia.com.br/java-collections-como-utilizar-collections/18450  		
	}
	
}
