import { Component, OnInit } from '@angular/core';
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
export class DiarioComponent implements OnInit{

  lancamento : Diario[] = [];

  // selectedRow : number | null = null;
  expandedRowIndex: number | null = null;

  constructor( 
    private diarioService: DiarioService,
    private route : Router
  ) {}

  ngOnInit() : void {
    // this.getLancamentos();
    this.diarioService.getLancamentos().subscribe((data: Diario[]) => {
      this.lancamento = data;
    });
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

  // Método para deletar um Lançamento
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



  // onRowClick(index: number): void {
  //   // Alterna a seleção da linha
  //   this.selectedRow = this.selectedRow === index ? null : index;
  // }

  toggleRow(index: number) {
    this.expandedRowIndex = this.expandedRowIndex === index ? null : index;
  }

}
