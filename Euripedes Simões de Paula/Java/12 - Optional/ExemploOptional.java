package Optional;

import java.util.Optional;

public class SegundoOptional {

	public static void main(String[] args) {
		
/*

O código a seguir manipulará objeto 'Usuario' com operações de 
verificação e transformação dos valores contidos dentro do 'Optional'.
 
*/		
		
// 		Criando um Objeto da classe 'Usuario'
		Usuario usuario = new Usuario(1, "Lucas", 28);
		
// 		Encapsulando o Objeto 'Usuario' no usuarioOptional
		Optional<Usuario> usuarioOptional = Optional.ofNullable(usuario);		
	
// 		'map()': é utilizado como um laço condicional.
		usuarioOptional.map(resp -> resp.getNome())//map(Usuario::getNome))
		
// 			'filter()': define um filtro com determinada condição
			.filter(nomeUsuario -> nomeUsuario
					
// 					A condição que o filtro precisa
					.startsWith("L"))
			
// 			'orElseThrow()' + RuntimeException: para caso condição for falsa 
			.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
//		Ao exibir o nome do usuario, não esqueça de usar métodos.
		System.out.println("\nNome do Usuário: " + usuarioOptional

//				O método 'get()' acessa o objeto para conseguir 
//				executar o método 'getNome()' na Classe Usuario
				.get().getNome());
	}
	
}
