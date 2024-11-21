import { Component, OnInit } from '@angular/core';
import { DiarioService } from '../services/diario.service';
import { Diario } from '../model/Diario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-diario',
  templateUrl: './diario.component.html',
  styleUrl: './diario.component.css'
})
export class DiarioComponent implements OnInit{

  // Objeto
  lancamento : Diario[] = [];

  // Variável para expandir as linhas
  expandedRowIndex: number | null = null;

  constructor( 
    private diarioService: DiarioService,
    private route : Router
  ) {}

  ngOnInit() : void {

    // Chamando o método GET Lançamento
    this.diarioService.getLancamentos().subscribe((data: Diario[]) => {
      this.lancamento = data;
    });
  }

  // Método GET para os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.lancamento = data;
    })
  }

  // Método PUT para o Lançamento
  updateLancamento( id : number ){
    this.route.navigate(['atualizar', id]);
  }

  // Método DELETE para o Lançamento
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

  // OUTROS MÉTODOS 

  // Definindo a expansão da linha com o ID
  toggleRow(index: number) {
    this.expandedRowIndex = this.expandedRowIndex === index ? null : index;
  }

  cores() {
    
  }



}
