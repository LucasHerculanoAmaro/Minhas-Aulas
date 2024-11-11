import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioComponent } from './diario/diario.component';
import { RazaoComponent } from './razao/razao.component';
import { BalanceteComponent } from './balancete/balancete.component';

const routes: Routes = [
  { path: '', redirectTo: '/diario', pathMatch: 'full' },
  { path: 'balancete', component: BalanceteComponent },
  { path: 'diario', component: DiarioComponent },
  { path: 'razao', component: RazaoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
