import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aluno } from './aluno';

@Injectable({
  providedIn: 'root'
})

// Neste documento vamos implementar os serviços que serão aplicados com base na API, desenvolvida anteriormente no Eclipse IDE.
export class AlunoService {

  // Vamos adicionar a base URL, a API que criamos no back-end
  private baseURL = "http://localhost:8080/api/alunos";

  constructor(
    // Agora declararemos um parâmetro que receberá um método Http.
    private httpClient : HttpClient ) { }

/* Método GET */
    // Nosso objetivo agora é criar o método "getLista()" para retornar a lista dos alunos, enviada por API.
    getLista() 

      // O uso de ":" define o tipo de uma variável, retorno de uma função ou o parâmetro de uma função
      :
    
      // Aqui estamos definindo o tipo de retorno da função, onde temos:
      Observable< Aluno[] > // que retorna um "Observable" com array de objetos "Aluno". 
      // Utilizamos o "Observable" para lidar com operações assíncronas. Como, por exemplo, solicitações HTML.
      {
      
      // Para este método vamos retornar o parâmetro "httpClient", criado no "constructor". 
      return this.httpClient
        //que receberá a requisição do método HTTP GET através do método "get" e que espera a resposta do objeto "Aluno".
        .get<Aluno[]>(
          
          // Vamos utilizar a crase para criar uma interpolação. Dessa forma, podemos implementar expressões de código dentro da própria String.

          // Template Literals - quando usamos o crifrão e um ogo de chaves para interpolação de strings (inserir valores de variáveis dentro da string).
          `
            ${ 

              // Vamos passar a variável que contém o link de nossa API.
              this.baseURL 
            }
          
          `
        );

      // Vamos ao documento "lista.component.ts", e no "constructor()" faremos mais algumas modificações.
    }
}
/* REFERÊNCIAS 

->  Observable in Angular
    https://angular.io/guide/observables-in-angular

->  Diferença de ' ' e ` `
    https://pt.stackoverflow.com/questions/188004/diferen%C3%A7a-de-e

->  Angular + Spring Boot CRUD Full Stack App - 11 - Connecting Angular with List Employee REST API
    https://www.youtube.com/watch?v=poQLnZU0eHc&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=12

*/