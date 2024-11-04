import { Component, OnInit } from '@angular/core';
import { Aluno } from '../aluno';
import { AlunoService } from '../aluno.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.css'
})
export class ListaComponent implements OnInit {

  // Aqui adicionamos as informações da classe "Aluno", que está em um array, na variável "alunos".
  alunos : Aluno[] = [];

  constructor(
    // Vamos criar um parâmetro atribuindo a classe "AlunoService" que implementamos.
    private alunoService : AlunoService,

    // Depois de implementar o parâmetro "AlunoService", vamos implementar o método GET logo abaixo do "ngOnInit()"; observe o método "getAlunos()".
  
    // Importando o "Router"
    private router: Router
    // Agora volte ao método "updateAluno"
  
  ) {}

  ngOnInit(): void {

    /*  NOTA PARA O PROFESSOR: Esta implementação deve ser feita apenas na segunda parte, ao buscar o dados do MySQL via API.  */
    this.getAlunos();
  
  // Primeira definição para listas

    /* Antes de puxar os dados via API, vamos simular como os dados, em formato JSON, serão passados para a tabela; e assim, podemos observar como os dados serão apresentados na lista.    */
    // this.alunos = [
    //   {  "id": 1,  "nome": "Lucas",  "sobrenome": "Herculano", "email": "lucash.96@hotmail.com"  },
    //   {  "id": 2,  "nome": "Juliana",  "sobrenome": "Araújo",  "email": "ju.s.araujo@hotmail.com"}
    // ];

    // Agora vamos retornar ao documento "app.component.html" para continuarmos nossa aula.

  // Fim da primeira definição para listas
    
  } 

  // Método GET para lista
  private getAlunos() {

    // Vamos chamar o parâmetro do método construtor "alunoService", pois será necessário aplicar alguns métodos neste parâmetro.
    this.alunoService

      // Chamamos o método "getLista", implementado na camada de serviço.
      .getLista()

      // O método "subscribe()" é usado para receber valores emitidos por um "Observable", a fim de executar uma ação.
      .subscribe(

        // Vamos utilizar uma função chamada de "Arrow Function", bem similar a "Expressão Lambda". Nosso objetivo é criar uma função anônima para manipular dados recebidos.
        data => { // data: representa os dados recebidos

          // Definimos que "this.alunos", uma variável array, receberá os valores de "data".
          this.alunos = data;
        }
      );

  /* 
  Vamos testar este método, e para isso seguiremos os seguintes passos:
    * Comente o registro criado no "ngOnInit()", para não atrapalhar a visualização dos dados.
    * Criar registros no banco de dados.
    * Vamos executar a aplicação no Eclipse.
    * Vamos executar a aplicação aqui no Visual Studio Code.
    * Agora abra a página WEB "http://localhost:4200/" e veja se os registro que você adicionou no banco de dados está presente na nossa planilha. Se os testes foram bem sucedidos, e os dados do banco de dados estão aparecendo na página, agora vamos fazer algumas implementações no documento "app-routing.modules.ts".
  */
  }

  updateAluno(

    // Crie o parâmetro id
    id: number
  ) {

    // No método "constructor", importe o "Router".

    // Chamando o método "navigate"
    this.router.navigate(['atualizar', id]);

    /* Agora vamos testar o que implementamos. Se você estiver com a aplicação em funcionamento, você verá que ao clicar no botão "Atualizar", você será encaminhado para a tela de update. Agora siga para o documento "atualizar.component.html". */
  }

  deleteAluno(

    // Parâmetro ID
    id: number
  ){
    /* Agora vamos ao "aluno.service" para criar o metodo de serviço DELETE. */

    /* Agora vamos chamar o método de serviço, implementado na camada de serviço 
    OBS: Neste método, as implementações são parecidas; se você chegou até aqui, não terá duvidas do que as seguintes implementações a seguir faz. */
    this.alunoService.deleteAluno(id)

      // Chame o Método "subscribe"  
      .subscribe({

        // Criando um "Arrow Function" para trabalhar com logs de console e chamar o método "getAlunos"
        next : data => {
          console.log(data),
          this.getAlunos()
        },

        // Criando um "Arrow Function"
        error : error => console.log(error)
      })

    /* Agora, na página web, teste o método DELETE, e veja se ele está funcionando corretamente. Testado o método, e ele está funcionando como esperado, finalizamos aqui a implementação do método CRUD. 
    
    Agora vamos trabalhar com a última implementação, onde vamos criar uma tela que apresentará detalhes dos alunos. 
    Use o comando "ng g c detalhes" no terminal. Depois vamos ao "app-routing.modules.ts" configurar o caminho do componente. */
  }

  details(
    
    // Vamos criar o parâmetro id
    id : number){

      // Vamos carmar o método "navegate"
    this.router.navigate(['detalhes', id]);

    // Ao testar o método, clicando no botão "Detalhes", somos direcionado para a tela de detalhes.
  }

}

/*  REFERÊNCIAS 

->  Dúvida sobre subscribe
    https://cursos.alura.com.br/forum/topico-duvida-sobre-subscribe-199658#:~:text=Primeiramente%2C%20%C3%A9%20importante%20entender%20que,a%C3%A7%C3%A3o%20a%20partir%20desses%20valores.

->  JavaScript - Arrow Functions
    https://www.macoratti.net/19/02/js_arrow1.htm#:~:text=As%20arrow%20functions%20permitem%20ter,que%20usar%20a%20palavar%20return.&text=O%20conceito%20de%20funcionamento%20do,(modo%20strict%20ou%20n%C3%A3o).  
  
*/
