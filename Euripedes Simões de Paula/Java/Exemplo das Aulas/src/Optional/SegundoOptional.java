package Optional;

import java.util.Optional;

public class SegundoOptional {

	public static void main(String[] args) {
		
/*

O c�digo a seguir manipular� objeto 'Usuario' com opera��es de 
verifica��o e transforma��o dos valores contidos dentro do 'Optional'.
 
*/		
		
// 		Criando um Objeto da classe 'Usuario'
		Usuario usuario = new Usuario(1, "Lucas", 28);
		
// 		Encapsulando o Objeto 'Usuario' no usuarioOptional
		Optional<Usuario> usuarioOptional = Optional.ofNullable(usuario);		
	
// 		'map()': � utilizado como um la�o condicional.
		usuarioOptional.map(resp -> resp.getNome())//map(Usuario::getNome))
		
// 			'filter()': define um filtro com determinada condi��o
			.filter(nomeUsuario -> nomeUsuario
					
// 					A condi��o que o filtro precisa
					.startsWith("L"))
			
// 			'orElseThrow()' + RuntimeException: para caso condi��o for falsa 
			.orElseThrow(() -> new RuntimeException("Usu�rio n�o encontrado"));
		
//		Ao exibir o nome do usuario, n�o esque�a de usar m�todos.
		System.out.println("\nNome do Usu�rio: " + usuarioOptional

//				O m�todo 'get()' acessa o objeto para conseguir 
//				executar o m�todo 'getNome()' na Classe Usuario
				.get().getNome());
	}
	
}
