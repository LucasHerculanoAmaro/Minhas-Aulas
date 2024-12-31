import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioLogin } from '../model/Login';
import { AuthService } from '../services/authService';
import { environment } from '../../environment';
import { AlertasComponent } from '../alertas/alertas.component';
import { error } from 'console';
import { EventEmitter } from 'stream';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  usuarioLogin : UsuarioLogin = new UsuarioLogin();
  
  constructor( 
    private router : Router ,
    private authService : AuthService
  ) {}

  ngOnInit(){}

  async login() {
    this.authService.login(this.usuarioLogin.usuario, this.usuarioLogin.senha).subscribe({
      next : (response) => {
        this.authService.storeToken(response.token);
        this.router.navigate(['/diario']);
      },
      error : (error) => {
        alert('Erro de credenciais');
        console.error("Erro:", error)
      }
    })
  }

}
