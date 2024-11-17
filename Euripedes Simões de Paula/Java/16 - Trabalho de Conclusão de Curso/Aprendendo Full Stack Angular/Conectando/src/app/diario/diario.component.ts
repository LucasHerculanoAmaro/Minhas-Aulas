import { Component } from '@angular/core';
import { DiarioService } from '../services/diario.service';
import { Diario } from '../model/Diario';
import { empty } from 'rxjs';
import { error } from 'console';

@Component({
  selector: 'app-diario',
  templateUrl: './diario.component.html',
  styleUrl: './diario.component.css'
})
export class DiarioComponent {

  lancamento : Diario[] = [];

  // total!: string;

  constructor( private diarioService: DiarioService ) {}

  ngOnInit() : void {
    this.getLancamentos();
  }

  // Método para buscar todos os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.lancamento = data;
    })
  }

  deleteLancamento( id: number ) {
    this.diarioService.deleteLancamento(id).subscribe({
      next : response => { 
        this.getLancamentos(), response
      },
      error : error => {
        console.log("Erro ao deletar lançamento", error)
      }
    })
  }


}
