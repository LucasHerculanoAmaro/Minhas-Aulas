import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioComponent } from './diario/diario.component';
import { RazaoComponent } from './razao/razao.component';
import { BalanceteComponent } from './balancete/balancete.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { HistoricoComponent } from './historico/historico.component';

const routes: Routes = [
  { path: '', redirectTo: '/diario', pathMatch: 'full' },
  { path: 'balancete', component: BalanceteComponent },
  { path: 'diario', component: DiarioComponent },
  { path: 'razao', component: RazaoComponent },
  { path: 'registrar', component: RegistrarComponent },
  { path: 'historico', component:HistoricoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
