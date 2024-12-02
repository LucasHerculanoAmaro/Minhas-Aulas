import { Component } from '@angular/core';
import { Historico } from '../model/Historico';
import { HistoricoService } from '../services/historico.service';

@Component({
  selector: 'app-historico',
  templateUrl: './historico.component.html',
  styleUrl: './historico.component.css'
})
export class HistoricoComponent {

  historico: Historico[] = [];

  constructor(
    private historicoService: HistoricoService
  ) { }

  ngOnInit(): void {

    // Chamando o método GET
    this.historicoService.getHistorico().subscribe((data: Historico[]) => {
      this.historico = data;
    })
  }

  // Método GET para os Históricos
  getHistoricos() {
    this.historicoService.getHistorico().subscribe(data => {
      this.historico = data;
    })
  }

}
