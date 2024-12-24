import { Component, OnInit } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';
import { start } from 'repl';

@Component({
  selector: 'app-diario-periodo',
  templateUrl: './diario-periodo.component.html',
  styleUrl: './diario-periodo.component.css'
})
export class DiarioPeriodoComponent implements OnInit {

  lancamentos: Diario[] = [];

  startDate!: string;
  endDate!: string;
  
  total: number = 0;

  constructor(private diarioService: DiarioService) { }

  ngOnInit() {}

  filtrarPorPeriodo() {
    console.log('Método filtrarPeriodo chamado') // Chat sugestão
    if (!this.startDate || !this.endDate) {
      alert('Selecione ambas as datas.');
      return;
    }

    try {

      const startDateFormatted = new Date(`${this.startDate}T00:00:00`).toISOString().split('T')[0];
      const endDateFormatted = new Date(`${this.endDate}T23:59:59`).toISOString().split('T')[0];

      console.log(`Datas formatadas: startDate=${startDateFormatted}, endDate=${endDateFormatted}`); // Chat sugestão

      this.diarioService.getLancamentosPorPeriodo(
        startDateFormatted, endDateFormatted
        // '2024-12-01', '2024-12-23'
      ).subscribe({
        next: data => {
          console.log('Lançamentos recebidos:', data)// Chat sugestão
          this.lancamentos = data.map(lancamento => ({
            ...lancamento,
            valor: lancamento.transacao.toLowerCase() === "débito" ? -Math.abs(lancamento.valor) : lancamento.valor
          }));
          this.saldoGeral;
        },
        error: error => console.error('Erro ao filtrar por período', error)
      });
    }
    catch (error) {
      console.error('Erro ao processar as datas:', error);
    }

  }

  // Método para somar os elementos filtrados
  get saldoGeral() : number {
    return this.lancamentos.reduce((acc, lancamento) => acc + lancamento.valor, 0);
  }

  // Método para calcular o total de Débito
  get totalDebito() : number {
    return this.lancamentos
      .filter(lancamento => lancamento.transacao.toLowerCase() === "débito")
      .reduce((acc, lancamento) => acc + Math.abs(lancamento.valor), 0);
  }

  // Método para calcular o total de Crédito
  get totalCredito() : number {
    return this.lancamentos
      .filter(lancamento => lancamento.transacao.toLowerCase() === "crédito")
      .reduce((acc, lancamento) => acc + lancamento.valor, 0);
  }

  // Métodos para determinar valores positivos
  isPositive() : boolean {
    return this.saldoGeral > 0;
  } 

  // Métodos para determinar valores positivos e negativos
  isNegative() : boolean {
    return this.saldoGeral < 0; 
  }

  // Método para Débito
  isDebito(transacao: string): boolean {
    return transacao.toLowerCase() === "débito";
  }

  // Método para Crédito
  isCredito(transacao: string): boolean {
    return transacao.toLowerCase() === "crédito";
  }

}


