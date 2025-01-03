import { Component, OnInit, ViewChild } from '@angular/core';
import { Usuario } from '../model/Usuario';
import { DiarioService } from '../services/diario.service';
import { UsuarioService } from '../services/usuario.service';
import { response } from 'express';
import { AlertasComponent } from '../alertas/alertas.component';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrl: './cadastrar.component.css'
})
export class CadastrarComponent {

  usuario : Usuario = new Usuario();

  token = localStorage.getItem('token');
  headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);

  @ViewChild(AlertasComponent) alertas! : AlertasComponent;

  constructor(
    private usuarioService : UsuarioService
  ) {}

  onSubmit() : void {
    this.createUsuario();
  }

  // CREATE
  createUsuario () {
    console.log('Token enviado:', this.token);
    this.usuarioService.cadastrar(this.usuario).subscribe({
      next : response => {
        console.log('Usuário cadastrado com sucesso!', response);
        this.alertas.mostrarAlerta('Usuário cadastrado com sucesso!', 'success');
      },
      error : error => {
        console.log('Erro ao cadastrar usuário', error);
        console.log('Token enviado:', this.token);
        this.alertas.mostrarAlerta('Erro ao registrar um Lançamento.', 'danger');
      }
    })
  }



}
