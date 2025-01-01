import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, Output, ViewChild } from '@angular/core';
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
export class LoginComponent implements OnInit, AfterViewInit {

  @ViewChild(AlertasComponent) alertas! : AlertasComponent;

  usuarioLogin : UsuarioLogin = new UsuarioLogin();

  constructor( 
    private router : Router ,
    private authService : AuthService
  ) {}

  ngAfterViewInit(){}

  ngOnInit(){}

  async login() {
    this.authService.login(this.usuarioLogin.usuario, this.usuarioLogin.senha).subscribe({
      next : (response) => {
        this.authService.storeToken(response.token);
        this.router.navigate(['/inicio']);
      },
      error : (error) => {
        
        if ( this.alertas ) {
          this.alertas.mostrarAlerta("Erro de credenciais!", 'danger');
        } else {
          console.error('AlertaCompobebt não está inicializado.')
        }

        console.error("Erro:", error)
      }
    })
  }

}
