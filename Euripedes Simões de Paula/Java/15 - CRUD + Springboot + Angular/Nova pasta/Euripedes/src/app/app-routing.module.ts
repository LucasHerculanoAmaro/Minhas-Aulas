import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaComponent } from './lista/lista.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { AtualizarComponent } from './atualizar/atualizar.component';

/* 
  Nesta classe vamos trabalhar com as rotas de nossa página.
  Sabe quando você adiciona "/nomePagina" em um site? Então, nosso objetivo é modular as rotas de nossa página, para que elas possam serem chamadas e para que cada rota faça algo específico. 
  Por exemplo, se você deseja ver uma tela que apenas mostre a lista dos Aluno, você pode configurar um componente e depois designar o nome que este componente terá. Este nome será chamado em um login e a tela de componente será apresentada ao usuário.
  Eu sei que ficou um pouco confuso, mas vamos praticar e você entenderá melhor.
*/

const routes: Routes = [

  // Adicionando rota para a Lista
  { 
    // Crinado um caminho com o nome "Alunos"
    path: 'alunos', 
    // Chamando o componente, dono do nome "Alunos"
    component: ListaComponent  
  },

  // Agora vamos criar uma rota para caso nenhum nome seja definido na URL
  {
    // Criando um caminho com nome vazio
    path: '',
    // Caso o caminho seja vazio, a página será direcionada para o caminho "Alunos"
    redirectTo: 'alunos',
    // Para garantir que não haja erros no redirecionamento
    pathMatch: 'full'
  },

  // Vamos criar o caminho "registrar" para o componente
  { path: 'registrar', component: RegistrarComponent },
  /* 
    Agora execute a aplicação e veja se a página para criar os Alunos é carregada. No Menu, clique em "Registrar Aluno", e veja se a tela será carregada e as palavras "registrar works!" aparece na tela.

    Agora que criamos o componente "registrar", vamos implementar as demais funcionalidades para o método CREATE. Primeiramente, vamos ao documento "registrar.component.html" e vamos configurar criar um menu. Acompanhe a implementação na linha 32.   
    
  
  */

  /* Aqui vamos configurar o caminho para o modulo "atualizar". */
  { path: 'atualizar', component: AtualizarComponent }
  // Agora siga para o documento "lista.component.ts", e lá vamos confiurar o menu.
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

/*  REFERÊNCIAS

-> Add navigation with routing
  https://v17.angular.io/tutorial/tour-of-heroes/toh-pt5
  
-> Angular + Spring Boot CRUD Full Stack App - 12 - Routing and Navigation in Angular App
  https://www.youtube.com/watch?v=TUYyW0l8fiA&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=12  

-> pathMatch nao entendi
  https://cursos.alura.com.br/forum/topico-pathmatch-nao-entendi-205793  

-> Angular + Spring Boot CRUD Full Stack App - 14 - Creating Angular Create Employee Component
  https://www.youtube.com/watch?v=yYlMsNdAXJ4&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=15
  */

  