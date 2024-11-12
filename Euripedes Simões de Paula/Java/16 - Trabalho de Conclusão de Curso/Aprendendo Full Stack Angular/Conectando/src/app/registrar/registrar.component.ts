import { Component } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {

  lancamento : Diario = new Diario();

  constructor( private diarioService : DiarioService ){}

  // Método para criar um Lançamento 
  // createLancamento( novoLancamento : any ){
  onSumit(): void {
    this.diarioService.createLancamento(this.lancamento).subscribe({
      next : response => {
        console.log( 'Lançamento criado com sucesso: ', response )
      },
      error : error => {
        console.error( 'Erro ao criar o lançamento', error )
      }
    })
  }

}
