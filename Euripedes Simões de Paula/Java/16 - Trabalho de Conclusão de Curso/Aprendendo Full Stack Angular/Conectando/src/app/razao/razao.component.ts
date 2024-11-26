import { Component, OnInit } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';

@Component({
  selector: 'app-razao',
  templateUrl: './razao.component.html',
  styleUrl: './razao.component.css'
})
export class RazaoComponent implements OnInit {

  razao: Diario[] = [];

  constructor(private diarioService: DiarioService) { }

  ngOnInit(): void {
    this.getLancamentos();
  }

  // Soma do Crédito
  get totalCredito(): number {

    return this.razao
      .filter((item) => item.transacao === "CRÉDITO")
      .reduce((total, item) => total + item.valor, 0);
  }

  // Soma do Débito
  get totalDebito(): number {
    return this.razao
      .filter((item) => item.transacao === "DÉBITO")
      .reduce((total, item) => total + item.valor, 0);
  }

  // Soma Total
  get saldoGeral(): number {
    return this.totalCredito - this.totalDebito;
  }

  // Método para buscar todos os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.razao = data;
    })
  }

  // Método para Débito
  isDebito(transacao: string): boolean {
    return transacao.toLowerCase() === "débito";
  }

  // Método para Crédito
  isCredito(transacao: string): boolean {
    return transacao.toLowerCase() === "crédito";
  }

  // Método para Total
  positiveNegative(status: boolean) {
    if ( this.totalCredito > this.totalDebito ) {
      status = true;
    } else {
      status = false;
    }
     
  }

}
