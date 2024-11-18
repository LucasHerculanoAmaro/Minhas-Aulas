import { Component } from '@angular/core';
import { DiarioService } from '../services/diario.service';
import { Diario } from '../model/Diario';
import { empty } from 'rxjs';
import { error } from 'console';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-diario',
  templateUrl: './diario.component.html',
  styleUrl: './diario.component.css'
})
export class DiarioComponent {

  lancamento : Diario[] = [];

  // total!: string;

  constructor( 
    private diarioService: DiarioService,
    private route : Router
  ) {}

  ngOnInit() : void {
    this.getLancamentos();
  }

  // Método para buscar todos os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.lancamento = data;
    })
  }

  updateLancamento( id : number ){
    this.route.navigate(['atualizar', id]);
  }

  deleteLancamento( id: number ) {
    this.diarioService.deleteLancamento(id).subscribe({
      next : data => { 
        console.log(data),
        this.getLancamentos()
      },
      error : error => {
        console.log( error)
      }
    })
  }


}
