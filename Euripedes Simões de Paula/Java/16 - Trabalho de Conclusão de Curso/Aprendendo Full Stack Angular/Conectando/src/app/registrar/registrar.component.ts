import { Component, ViewChild } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';
import { Router } from '@angular/router';
import { AlertasComponent } from '../alertas/alertas.component';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {

  // Objeto
  diario: Diario = {
    credito: { id: 1 },
    debito: { id: 2 },
    data: this.getCurrentDate(),
    historico: '',
    valor: 0,
    id: 0,
    transacao: ''
  }

  @ViewChild(AlertasComponent) alertas!: AlertasComponent;

  constructor(
    private diarioService: DiarioService,
    private router : Router
  ) { }

  // Método para criar um Lançamento 
  onSubmit(): void {

    // Lógica para Crédito e Débito com Caixa Alta e para Indefinido ou vazio
    if (this.diario.transacao === "credito") {
      this.diario.transacao = "CRÉDITO"
    } else if (this.diario.transacao === "debito") {
      this.diario.transacao = "DÉBITO"
    } else {
      this.diario.transacao = "Não Definido"
    }

    // Lógica para histórico não definidos
    if (!this.diario.historico || this.diario.historico.trim() === "") {
      this.diario.historico = "Sem Registro";
    }

    // Método CREATE
    this.diarioService.createLancamento(this.diario).subscribe({

      next: response => {

        console.log('Lançamento criado com sucesso: ', response);

        this.alertas.mostrarAlerta("Lançamento criado com sucesso.", 'success');

        setTimeout(() => {
          this.goToLista();
        }, 3000);

      },
      error: error => {

        console.error('Erro ao criar o lançamento', error);

        this.alertas.mostrarAlerta("Erro ao registrar um Lançamento.", 'danger');
        
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

  // Método para direcionamento de Rota
  goToLista() {
    this.router.navigate(['/diario']);
  }

}