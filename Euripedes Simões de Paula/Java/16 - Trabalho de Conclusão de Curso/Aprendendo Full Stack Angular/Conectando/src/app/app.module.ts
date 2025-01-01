import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiarioComponent } from './diario/diario.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { HistoricoComponent } from './historico/historico.component';
import { AtualizarComponent } from './atualizar/atualizar.component';
import { DiarioPeriodoComponent } from './diario-periodo/diario-periodo.component';
import { AlertasComponent } from './alertas/alertas.component';
import { LoginComponent } from './login/login.component';
import { InicioComponent } from './inicio/inicio.component';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { CadastrarComponent } from './cadastrar/cadastrar.component';

@NgModule({
  declarations: [
    AppComponent,
    DiarioComponent,
    RegistrarComponent,
    MenuComponent,
    HistoricoComponent,
    AtualizarComponent,
    DiarioPeriodoComponent,
    AlertasComponent,
    LoginComponent,
    InicioComponent,
    CadastrarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    provideHttpClient(withFetch()),
    provideClientHydration(),
    {
      provide : HTTP_INTERCEPTORS,
      useClass : AuthInterceptorService,
      multi : true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
