import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioComponent } from './diario/diario.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { HistoricoComponent } from './historico/historico.component';
import { AtualizarComponent } from './atualizar/atualizar.component';
import { DiarioPeriodoComponent } from './diario-periodo/diario-periodo.component';

const routes: Routes = [
  { path: '', redirectTo: '/diario', pathMatch: 'full' },
  { path: 'diario', component: DiarioComponent },
  { path: 'periodico', component: DiarioPeriodoComponent},
  { path: 'registrar', component: RegistrarComponent },
  { path: 'historico', component:HistoricoComponent },
  { path: 'atualizar/:id', component: AtualizarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
