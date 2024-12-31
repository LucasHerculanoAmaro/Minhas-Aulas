import { Component } from '@angular/core';
import { AuthService } from './services/authService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Conectando';

  constructor( public authService : AuthService ){}
}
