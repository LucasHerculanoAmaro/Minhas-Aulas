import { Component, OnInit } from '@angular/core';
import { Aluno } from '../aluno';
import { AlunoService } from '../aluno.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css',
})
export class RegistrarComponent implements OnInit {

  // Criando um novo objeto com base na classe "aluno.ts".
  aluno : Aluno = new Aluno();
  // Agora siga para o documento "registrar.component.html", onde continuaremos as nossas implementações.

  constructor(

    // Atribuindo a classe de serviço no parâmetro.
    private alunoService : AlunoService,
    // Agora siga abaixo o método "ngOnInit", lá implementaremos o método respponsável por salvar o Objeto

    // Atribuindo a classe "Router", importada do angular 
    private router : Router // Retorne ao método "goToLista"
  ) {}

  onSubmit(){
    // Vamos criar um registro de console apenas para observarmos como serão enviados os dados do objeto na página WEB. Então, se sua aplicação estiver em funcionamento, vamos até a página, em Cadastrar um Aluno, e lá vamos inserir as informações do Aluno e clicar em enviar. Depois, aperte "F12", vá em console; você verá que as informações digitadas e enviadas estará dentro de um array. É dessa forma que as informações são enviadas em uma requisição HTTP e viaja até serem registradas no banco de dados.  
    console.log(this.aluno);

    // Na próxima aula vamos implementar o envio das informações utilizando o método HTTO POST. Também veremos os dados sendo registrados no MySQL. Para isso, acompanhe o documento "aluno.service.ts", na linha 58.
  
  
    // chamando o método "saveAluno"
    this.saveAluno();
    // Depois de salvar este método, siga para o método "saveAluno", no primeiro "Arrow Function", abaixo de "console.log"
  }

  ngOnInit(): void {  }

  // Método para salvar os alunos
  saveAluno(){

    // Referênciando o parâmetro do "constructor"
    this.alunoService

      // Agora precisaremos do método "createAluno", implementado na camada de serviço
      .createAluno(

        // Indicamos o nosso Objeto
        this.aluno)

        // Vamos receber os dados do "Observable" com o "subscribe"
        .subscribe({
          
          // Vamos criar uma função anonima "Arrow Function"
          next : data => {

            // Aqui vamos implementar um "console.log" similar ao que realizamos no "onSubmit"
            console.log(

              // Indicamos a nossa função
              data
            );

            // Chame o método "goToLista" --> Faça isso somente após implementar o método "goToLista"
            this.goToLista();

            /* Agora teste a aplicação, e veja se você consegue criar um registro. Na próxima aula, implementaremos o método UPDATE; atualizaremos registro por meio da busca por ID. Para isso, crie um novo componente com o comando "ng g c atualizar", depois siga para a linha 42 do "app-routing.modules.ts". */
          },
          
          // Utilizaremos um "Arrow Function" para trabalhar com erros ao console
          error: error => 
            
            // Aqui implementamos um novo "console.log"
            console.log(

              // Finalizamos referênciando a nossa função
              error 
            )
          
        });
  }

  // Agora vamos criar um método que direciona o usuário para a lista, depois do usuário ser criando.
  goToLista() {

    // Vá ao método "constructor" e implemente a classe "Router", depois retorne para este lugar

    // Vamos buscar o parâmetro "router"
    this.router

      // chame o método "navegate()"
      .navigate(

        [
          
          // Chame o end-point para a retornar a lista
          '/alunos'

        ]

      );

    // Agora vá ao "onSubmit" e chame o "this.saveAluno"
  }

}

/* REFERÊNCIA 

-> Angular + Spring Boot CRUD Full Stack App - 15 - Angular Create Employee Form Handling
  https://www.youtube.com/watch?v=RTCRYdF7FBA&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=16

-> Angular + Spring Boot CRUD Full Stack App - 16 - Connecting Angular with Create Employee REST API
  https://www.youtube.com/watch?v=bh-HDtx-ppw&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=16
    

*/