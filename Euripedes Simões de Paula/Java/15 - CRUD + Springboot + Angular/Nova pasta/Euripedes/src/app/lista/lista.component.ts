import { Component, OnInit } from '@angular/core';
import { Aluno } from '../aluno';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.css'
})
export class ListaComponent implements OnInit {

  // Aqui adicionamos as informações da classe "Aluno", que está em um array, na variável "alunos".
  alunos: Aluno[] = [];

  constructor() {}

  ngOnInit(): void {

    /* Vamos simular dados em formato JSON a fim de passá-os na tabela. Dessa forma, podemos observar
    como os dados serão apresentados na lista.    */
    this.alunos = [
      {  "id": 1,  "nome": "Lucas",  "sobrenome": "Herculano", "email": "lucash.96@hotmail.com"  },
      {  "id": 2,  "nome": "Juliana",  "sobrenome": "Araújo",  "email": "ju.s.araujo@hotmail.com"}
    ];

  }

}
