import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioComponent } from './diario/diario.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { HistoricoComponent } from './historico/historico.component';
import { AtualizarComponent } from './atualizar/atualizar.component';
import { DiarioPeriodoComponent } from './diario-periodo/diario-periodo.component';
import { LoginComponent } from './login/login.component';
import { InicioComponent } from './inicio/inicio.component';
import { AuthGuard } from './security/authGuard';

const routes: Routes = [
  { path: '', redirectTo: '/diario', pathMatch: 'full' },
  { path: 'diario', component: DiarioComponent, canActivate: [AuthGuard] },
  { path: 'periodico', component: DiarioPeriodoComponent, canActivate: [AuthGuard] },
  { path: 'registrar', component: RegistrarComponent, canActivate: [AuthGuard]  },
  { path: 'historico', component:HistoricoComponent, canActivate: [AuthGuard]  },
  { path: 'atualizar/:id', component: AtualizarComponent, canActivate: [AuthGuard]  },
  { path: 'login', component: LoginComponent },
  { path: 'inicio', component:InicioComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
