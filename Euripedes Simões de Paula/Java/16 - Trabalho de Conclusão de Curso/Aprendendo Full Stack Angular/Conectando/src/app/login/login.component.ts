import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioLogin } from '../model/Login';
import { AuthService } from '../services/authService';
import { environment } from '../../environment';
import { AlertasComponent } from '../alertas/alertas.component';
import { error } from 'console';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  usuarioLogin : UsuarioLogin = new UsuarioLogin();
  
  constructor( 
    private router : Router ,
    private authService : AuthService
  ) {}

  async login() {
    this.authService.login(this.usuarioLogin.usuario, this.usuarioLogin.senha).subscribe({
      next : (response) => {
        console.log("Resposta do servidor:", response);
        this.authService.storeToken(response.token);
        console.log("Token armazenado:", response.token);
        this.router.navigate(['/diario']);
      },
      error : (error) => {
        alert('Erro de credenciais');
        console.error("Erro:", error)
      }
    })
  }

  // async login() {

  //   this.authService.login(this.usuarioLogin.usuario, this.usuarioLogin.senha).subscribe((resp : UsuarioLogin) => {
  //     this.usuarioLogin = resp

  //     environment.nome = this.usuarioLogin.usuario
  //     environment.tipo = this.usuarioLogin.tipo
  //     environment.token = this.usuarioLogin.token

  //     this.router.navigate(['/diario']);
  //   }, (error: { status: number; }) => {
  //     if (error.status == 500) {
  //       alert('Usuário ou senha incorretos!')
  //     }
  //   })

  // }

  // logout() {
  //   localStorage.removeItem('token');
  //   this.router.navigate(['/login']);

  //   console.log('Usuário desconectado.');
  // }

}
