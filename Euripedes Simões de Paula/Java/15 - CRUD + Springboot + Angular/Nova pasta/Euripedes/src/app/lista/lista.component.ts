import { Component, OnInit } from '@angular/core';
import { Aluno } from '../aluno';
import { AlunoService } from '../aluno.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.css'
})
export class ListaComponent implements OnInit {

  // Aqui adicionamos as informações da classe "Aluno", que está em um array, na variável "alunos".
  alunos: Aluno[] = [];

  constructor(
    // Vamos criar um parâmetro atribuindo a classe "AlunoService" que implementamos.
    private alunoService : AlunoService
  ) {}

  ngOnInit(): void {

    /*  NOTA PARA O PROFESSOR: Esta implementação deve ser feita apenas na segunda parte, ao buscar o dados do MySQL via API.  */
    this.getAlunos();
  
  // Primeira definição para listas

    /* Antes de puxar os dados via API, vamos simular como os dados, em formato JSON, serão passados para a tabela; e assim, podemos observar como os dados serão apresentados na lista.    */
    this.alunos = [
      {  "id": 1,  "nome": "Lucas",  "sobrenome": "Herculano", "email": "lucash.96@hotmail.com"  },
      {  "id": 2,  "nome": "Juliana",  "sobrenome": "Araújo",  "email": "ju.s.araujo@hotmail.com"}
    ];

    // Agora vamos retornar ao documento "app.component.html" para continuarmos nossa aula.

  // Fim da primeira definição para listas
  
    
  } 

  // Método GET para lista
  private getAlunos() {
    this.alunoService.getLista().subscribe( data => {
      this.alunos = data;
    })
  }

}
