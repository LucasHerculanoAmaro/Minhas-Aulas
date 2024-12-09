import { Component, OnInit } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';

@Component({
  selector: 'app-diario-periodo',
  templateUrl: './diario-periodo.component.html',
  styleUrl: './diario-periodo.component.css'
})
export class DiarioPeriodoComponent implements OnInit {

  lancamentos: Diario[] = [];

  meses = [
    { nome: 'Janeiro', valor:'01' },
    { nome: 'Fevereiro', valor:'02' },
    { nome: 'Março', valor:'03' },
    { nome: 'Abril', valor:'04' },
    { nome: 'Maio', valor:'05' },
    { nome: 'Junho', valor:'06' },
    { nome: 'Julho', valor:'07' },
    { nome: 'Agosto', valor:'08' },
    { nome: 'Setembro', valor:'09' },
    { nome: 'Outubro', valor:'10' },
    { nome: 'Novembro', valor:'11' },
    { nome: 'Dezembro', valor:'12' },
  ];

  anosDisponiveis: number[] = [];
  mesSelecionado: string = '';
  anoSelecionado: number = new Date().getFullYear();
  total: number = 0;

  constructor( private diarioService: DiarioService ){}

  ngOnInit() {

  }

  filtrarPorPeriodo() {
    const inicio = `${this.anoSelecionado}-${this.mesSelecionado}-01`;
    const fim = new Date(this.anoSelecionado, parseInt(this.mesSelecionado), 0).toISOString().slice(0, 10);

    

  }

}
