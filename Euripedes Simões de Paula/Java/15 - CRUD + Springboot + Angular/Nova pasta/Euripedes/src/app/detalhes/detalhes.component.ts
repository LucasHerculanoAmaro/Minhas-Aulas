import { Component } from '@angular/core';
import { Aluno } from '../aluno';
import { ActivatedRoute } from '@angular/router';
import { AlunoService } from '../aluno.service';

@Component({
  selector: 'app-detalhes',
  templateUrl: './detalhes.component.html',
  styleUrl: './detalhes.component.css'
})

export class DetalhesComponent {

  // Vamos criar uma variável ID
  id! : number

  // Vamos importa a nossa classe "Aluno"
  aluno! : Aluno

  constructor(

    // Vamos importar um ActivatedRoute
    private route : ActivatedRoute,
   
    private alunoService : AlunoService){

      // Vamos atribuir o parâmetro 'id' do 'params' a variável 'id'
      this.id = this.route.snapshot.params['id'];

      // Criando um novo objeto
      this.aluno = new Aluno();

      // Chamando o método 'getAlunoById'
      this.alunoService.getAlunoById(

        // Vamos chamar o 'id'
        this.id )

        // Vamos utilizar o método 'subcribe'
        .subscribe({

          // Criando um "Arrow Function" para eclarar uma função anônima
          next: data => {
            this.aluno = data;
          }
        })

      // Feito esta implementação, siga para o "detalhes.component.html", para implementar a página WEB.
    }
}
