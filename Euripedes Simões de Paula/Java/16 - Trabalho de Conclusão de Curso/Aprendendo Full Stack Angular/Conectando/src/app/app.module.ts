import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiarioComponent } from './diario/diario.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { HistoricoComponent } from './historico/historico.component';
import { AtualizarComponent } from './atualizar/atualizar.component';
import { DiarioPeriodoComponent } from './diario-periodo/diario-periodo.component';

@NgModule({
  declarations: [
    AppComponent,
    DiarioComponent,
    RegistrarComponent,
    MenuComponent,
    HistoricoComponent,
    AtualizarComponent,
    DiarioPeriodoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
