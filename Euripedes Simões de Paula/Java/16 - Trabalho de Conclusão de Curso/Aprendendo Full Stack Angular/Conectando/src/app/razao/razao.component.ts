import { Component } from '@angular/core';
import { Razao } from '../model/Razao';
import { RazaoService } from '../services/razao.service';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';

@Component({
  selector: 'app-razao',
  templateUrl: './razao.component.html',
  styleUrl: './razao.component.css'
})
export class RazaoComponent {

  razao : Diario[] = [];

  constructor( private diarioService: DiarioService ) {}

  ngOnInit() : void {
    this.diarioService.getLancamentos().subscribe((data) => {
      this.razao = data
    });

    this.getLancamentos();
  }

  get totalValor() : number {
    return this.razao.reduce((total, item) => total + item.valor, 0);
  }

  // Método para buscar todos os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.razao = data;
    })
  }

}
