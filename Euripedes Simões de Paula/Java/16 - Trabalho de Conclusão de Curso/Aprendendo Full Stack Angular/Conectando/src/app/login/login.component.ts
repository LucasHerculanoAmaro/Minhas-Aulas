import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Usuario } from '../model/Usuario';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username : string = '';
  password : string = '';

  constructor( private http : HttpClient , private router : Router) {}

  async logar() {

    try {

      console.log('Username:', this.username);
      console.log('Password:', this.password);

      const body = {
        id: null,
        nome: null,
        usuario: this.username,
        foto: null,
        tipo: null,
        senha: this.password,
        token: null
      };

      const headers = {
        'Content-Type': 'application/json'
      };

      this.http.post('http://localhost:8080/api/usuarios/logar', body)
        .subscribe({
          next: (response : any) => {
            
            console.log('Login bem-sucedido:', response);

            const token = response.token;

            if (token) {
              localStorage.setItem('authToken', token);
            };

            this.router.navigate(['/diario']);
          },
          error: error => console.error('Erro no login:', error)  
        }
        
      );
  
      // if ( response.ok ) {
      //   const token = await response.json();
      //   localStorage.setItem('token', token);
      //   console.log('Corpo da requisição:', body); // Depuração
      //   alert('Login realizado com sucesso!');
      //   window.location.href = '/diario';
      // } else {
      //   console.log('Corpo da requisição:', body); // Depuração
      //   alert('Login ou senha incorretos');
      // }

    } catch (error) {
      console.error('Erro ao realizar login:', error);
      alert('Erro no servidor. Tente novamente mais tarde.');
    }

  }

}
