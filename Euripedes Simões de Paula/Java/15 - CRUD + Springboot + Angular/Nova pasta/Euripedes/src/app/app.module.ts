import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaComponent } from './lista/lista.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { FormsModule } from '@angular/forms';
import { AtualizarComponent } from './atualizar/atualizar.component';

@NgModule({
  declarations: [
    AppComponent,
    ListaComponent,
    RegistrarComponent,
    AtualizarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule // Ao tentar importar este modulo, pode ser que surja problemas de importação. Você terá que fazer a importação manualmente, escrevendo o conteúdo que está na linha 9 deste documento.
    // Perceba que o erro em tela desapareceu, e agora vamos trabalhar para deixar os eventos funcionais. Siga para o documento "app.registrar.ts", e no "OnInit()" vamos fazer algumas implementações.
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
