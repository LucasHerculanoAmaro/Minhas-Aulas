import { Component } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {

  // lancamento : Diario = new Diario();
  
  diario: Diario = {
    credito: { id: 1 },
    debito: { id: 2 },
    data: this.getCurrentDate(),
    historico: '',
    valor: 0,
    id: 0,
    transacao: ''
  };

  constructor( private diarioService : DiarioService ){}

  // Método para criar um Lançamento 
  onSubmit(): void {

    if (this.diario.transacao === "credito") {
      this.diario.transacao = "CRÉDITO"
    } else if (this.diario.transacao === "debito") {
      this.diario.transacao = "DÉBITO"
    }

    this.diarioService.createLancamento(this.diario).subscribe({
      next : response => {
        console.log( 'Lançamento criado com sucesso: ', response )
      },
      error : error => {
        console.error( 'Erro ao criar o lançamento', error )
      }
    })
  }

  // Método para pegar a data atual
  getCurrentDate(): string {
    const date = new Date();
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

}
