import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

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

      const body = {
        usuario: this.username,
        senha: this.password,
        token: null
      };

      this.http.post('http://localhost:8080/api/usuarios/logar', body)
        .subscribe({
          next: (response : any) => {

            console.log('Resposta recebida:', response);

            const token = response.token;

            if (token) {
              console.log('Token recebido:', token);
              localStorage.setItem('Token', token);
              this.router.navigate(['/diario']);

            } else {
              console.log('Nenhum token recebido.')
            }
            
          },
          error: error => console.error('Erro no login:', error)  
        }
        
      );

    } catch (error) {
      console.error('Erro ao realizar login:', error);
      alert('Erro no servidor. Tente novamente mais tarde.');
    }

  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);

    console.log('Usuário desconectado.');

    this.username = '';
    this.password = '';
  }

}
