import { Component, OnInit } from '@angular/core';
import { Aluno } from '../aluno';

@Component({
  selector: 'app-alunos-lista',
  standalone: true,
  imports: [],
  templateUrl: './alunos-lista.component.html',
  styleUrl: './alunos-lista.component.css'
})

/* 

Se houver erros, e eles forem similares ao "Não é possível localizar o módulo '@angular/core'. Você quis definir a opção 'moduleResolution' como 'node' ou adicionar aliases à opção 'paths'?ts(2792)", utilizar a sugestão abaixo poderá ajudar a resolver o erro: 

  {
    "compilerOptions": {
      "moduleResolution": "node",
      "baseUrl": "./",
      "paths": {
        "@angular/*": ["node_modules/@angular/*"]
      }
    }
  }

A opção moduleResolution: "node" é crucial, pois indica que o TypeScript deve resolver módulos como o Node.js

*/ 

export class AlunosListaComponent {

  alunos: Aluno[] = [];

  constructor() {}

  ngOnInit(): void {

    this.alunos = [
      
      {  "id": 1,  "nome": "Lucas",  "sobrenome": "Herculano", "email": "lucash.96@hotmail.com"  },
      {  "id": 2,  "nome": "Juliana",  "sobrenome": "Araújo",  "email": "ju.s.araujo@hotmail.com"}
    ];

  }
}
