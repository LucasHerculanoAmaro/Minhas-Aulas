import { Component } from '@angular/core';
import { AlunoService } from '../aluno.service';
import { Aluno } from '../aluno';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-atualizar',
  templateUrl: './atualizar.component.html',
  styleUrl: './atualizar.component.css'
})

export class AtualizarComponent {

  // Vamos precisar instânciar um novo objeto
  aluno : Aluno = new Aluno();

  // Vamos criar uma variável id
  id!: number;

  constructor (

    // Vamos importar a nossa classe de serviço
    private alunoService : AlunoService,

    // Vamos importar o "ActivatedRouter" para fornecer informações da rota
    private route : ActivatedRoute
  ) {}

  ngOnInit() : void {

    // Vamos referênciar variável "id" com o parâmetro id
    this.id = this.route.snapshot.params['id'];

    this.alunoService.getAlunoById(

      // Vamos adicionar a variável
      this.id
    )

    // Como trabalhamos com "Observable", vamos chamar o "subscribe"
    .subscribe({

      // Agora vamos criar um "Arrow Function"
      next : data => {

        // Atribuindo o novo objeto à função
        this.aluno = data;
      },

      // Vamos criar um registro em caso de erro
      error : error => console.log(error)

      /* Ao executar a aplicação, ainda não conseguimos atualizar os registros, mas temos as rotas definidas, e ao clicar em nosso objeto com o botão "Atualizar", ele está sendo direcionado para a página "Atualizar Aluno". 
      
      Ao criar um método que buscar o objeto pelo ID, conseguimos buscar as informações de determinado aluno de maneira isolada.
      
      Por enquanto a nossa aula para por aqui, e na próxima aula vamos implementar a atualização dos registros no MySQL. */

    })
  }
}

/*  REFERÊNCIA  

-> ActivatedRouter
  https://v17.angular.io/api/router/ActivatedRoute

*/ 