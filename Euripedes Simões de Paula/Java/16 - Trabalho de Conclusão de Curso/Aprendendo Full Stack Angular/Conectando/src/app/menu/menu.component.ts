import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

constructor( private router : Router ) {}

username : string = '';
password : string = '';

logout() {
  localStorage.removeItem('token');
  this.router.navigate(['/login']).then(() => {
    window.location.reload();
  });

  console.log('Usuário desconectado.');

  this.username = '';
  this.password = '';
}

}
