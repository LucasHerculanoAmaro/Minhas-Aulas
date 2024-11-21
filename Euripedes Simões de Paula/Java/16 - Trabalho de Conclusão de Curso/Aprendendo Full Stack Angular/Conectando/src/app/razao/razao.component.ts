import { Component } from '@angular/core';
import { Razao } from '../model/Razao';
import { RazaoService } from '../services/razao.service';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';
import { style } from '@angular/animations';
import { HtmlTagDefinition } from '@angular/compiler';
import { verify } from 'crypto';

@Component({
  selector: 'app-razao',
  templateUrl: './razao.component.html',
  styleUrl: './razao.component.css'
})
export class RazaoComponent {

  razao : Diario[] = [];

  constructor( private diarioService: DiarioService ) {}

  ngOnInit() : void {
    this.getLancamentos();
  }

  // Soma do Crédito
  get totalCredito() : number {

    return this.razao
      .filter((item) => item.transacao === "CRÉDITO")
      .reduce((total, item) => total + item.valor, 0);
  }

  // Soma do Débito
  get totalDebito() : number {
    return this.razao
      .filter((item) => item.transacao === "DÉBITO")
      .reduce((total, item) => total + item.valor, 0);
  }

  // Soma Total
  get saldoGeral() : number {
    return this.totalCredito - this.totalDebito;
  }

  // Método para buscar todos os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.razao = data;
    })
  }

}
