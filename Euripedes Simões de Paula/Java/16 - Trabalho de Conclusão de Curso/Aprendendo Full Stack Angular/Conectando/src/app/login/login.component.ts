import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioLogin } from '../model/Login';
import { AuthService } from '../services/authService';
import { environment } from '../../environment';
import { AlertasComponent } from '../alertas/alertas.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  login : UsuarioLogin = new UsuarioLogin();
  
  constructor( 
    private http : HttpClient , 
    private router : Router ,
    private auth : AuthService,
    private alerta : AlertasComponent
  ) {}

  async logar() {

    this.auth.logar(this.login).subscribe((resp : UsuarioLogin) => {
      this.login = resp

      environment.nome = this.login.usuario
      environment.tipo = this.login.tipo
      environment.token = this.login.token

      this.router.navigate(['/diario']);
    }, erro => {
      if (erro.status == 500) {
        alert('Usuário ou senha incorretos!')
      }
    })

  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);

    console.log('Usuário desconectado.');
  }

}
