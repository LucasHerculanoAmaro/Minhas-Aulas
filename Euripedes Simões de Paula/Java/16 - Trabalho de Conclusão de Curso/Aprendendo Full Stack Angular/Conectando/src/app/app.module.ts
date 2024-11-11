import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiarioComponent } from './diario/diario.component';
import { RazaoComponent } from './razao/razao.component';
import { BalanceteComponent } from './balancete/balancete.component';

@NgModule({
  declarations: [
    AppComponent,
    DiarioComponent,
    RazaoComponent,
    BalanceteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
