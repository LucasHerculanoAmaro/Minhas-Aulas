package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import model.Usuario;
import repository.UsuarioRepository;

/*	Nesta aula, Implementaremos nesta classe configurações para carregar os 
	detalhes dos usuários.
	
 *	OBS: Já estudamos sobre sobre algumas anotações.
 	Pensando nisso, a partir dessa aula vamos economizar linhas, e evitar falar 
 	do que já foi explicado nas aulas anteriores (anotações, palavras reservadas
 	classes, implements, etc).		
*/


@Service
public class ConfigUsuarioDetailsService implements UserDetailsService{	
	
	@Autowired
    private UsuarioRepository usuarioRepository;

/*	@Override: Com esta anotação, indicamos que estamos sobrescrevendo um método,
 	e não criando um novo método.		*/	
    @Override
    
/*	UserDetails: Fornece informações essenciais ao usuário.
 
 *	OBS: Essa interface armazena informações do usuário e as encapsuladas em autenticações 
 	do objeto.	*/
    public UserDetails 
    
//	loadUserByUsername: este método simples de consulta o objeto no banco de dados.   
    loadUserByUsername(String username) 
    		
//    		Criando uma 'exception' em caso de usuário não encontrado
    		throws UsernameNotFoundException {
    	
//    	Procurando o usuário no repositório
        Usuario usuario = usuarioRepository.findByLogin(username)
        	
//        	Executa caso ousuário não seja encontrado
            .orElseThrow(
            		
//            		Iniciando a 'Expressão Lambda', indicando ao argumento uma 'Exception'.
            		() -> new UsernameNotFoundException("Usuário não encontrado")
            		);
        
//      Será retornado o login e senha, com base na classe 'Usuario'.
        return new org.springframework.security.core.userdetails
        		.User(
        				usuario.getLogin(), 
        				usuario.getSenha(), 
//        				Aqui retornamos (vazio) a autoridade que o usuário terá no sistema.
        				new ArrayList<>()
        				);
    }
	
/*	CONCLUSÃO
 
 * 	Dessa forma, terminamos a nossa aula de configuração para carregar os detalhes do usuário
	
 *	Na próxima aula, trabalharemos com a autenticação do usuário. Por isso siga para o pacote
 	'controller'.
*/    
    
}

/*	REFERÊNCIAS
 
 *	Qual a finalidade da @Override?
 	https://pt.stackoverflow.com/questions/22913/qual-a-finalidade-da-override
 	
 *	UserDetails
 	https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetails.html

*	[Dúvida] Diferença entre a classe UserDetailsService e controller que AuthenticationController
	https://cursos.alura.com.br/forum/topico-duvida-diferenca-entre-a-classe-userdetailsservice-e-controller-que-authenticationcontroller-383379#:~:text=O%20m%C3%A9todo%20loadUserByUsername%20de%20UserDetailsService,dados%20pelo%20username%20(login).

 *	Classe UsernameNotFoundException
 	https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UsernameNotFoundException.html


 */ 	
