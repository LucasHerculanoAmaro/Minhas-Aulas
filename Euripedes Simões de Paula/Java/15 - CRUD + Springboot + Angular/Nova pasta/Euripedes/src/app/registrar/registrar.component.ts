import { Component, OnInit } from '@angular/core';
import { Aluno } from '../aluno';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css',
})
export class RegistrarComponent implements OnInit {

  // Criando um novo objeto com base na classe "aluno.ts".
  aluno : Aluno = new Aluno();
  // Agora siga para o documento "registrar.component.html", onde continuaremos as nossas implementações.

  constructor() {}

  ngOnInit(): void {

    // Vamos criar um registro de console apenas para observarmos como serão enviados os dados do objeto na página WEB. Então, se sua aplicação estiver em funcionamento, vamos até a página, em Cadastrar um Aluno, e lá vamos inserir as informações do Aluno e clicar em enviar. Depois, aperte "F12", vá em console; você verá que as informações digitadas e enviadas estará dentro de um array. É dessa forma que as informações são enviadas em uma requisição HTTP e viaja até serem registradas no banco de dados.  
    console.log(this.aluno);

    // Na próxima aula vamos implementar o envio das informações utilizando o método HTTO POST. Também veremos os dados sendo registrados no MySQL.
  }

}

/* REFERÊNCIA 

-> Angular + Spring Boot CRUD Full Stack App - 15 - Angular Create Employee Form Handling
  https://www.youtube.com/watch?v=RTCRYdF7FBA&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=16

*/