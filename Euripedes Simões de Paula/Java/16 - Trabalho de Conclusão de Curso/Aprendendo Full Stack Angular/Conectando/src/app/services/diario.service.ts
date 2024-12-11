import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Diario } from '../model/Diario';

@Injectable({
  providedIn: 'root'
})
export class DiarioService {

  constructor(private http : HttpClient) { }

  // Lista de todos os Lançamentos
  getLancamentos() : Observable< Diario[] > {
    return this.http.get< Diario[] > ( 'http://localhost:8080/api/diario/transacoes' );
  }

  getLancamentoById( id: number ) : Observable < Diario > {
    return this.http.get< Diario > ( `http://localhost:8080/api/diario/transacoes/${id}` );
  }

  // Criar um Lançamento
  createLancamento( diario : Diario ) : Observable< Diario > {
    return this.http.post< Diario > ( 'http://localhost:8080/api/diario/registrar', diario );
  }

  // Atualizar um Lançamento
  updateLancamento(id: number, diario: Diario) : Observable< Diario > {
    return this.http.put< Diario > ( `http://localhost:8080/api/diario/atualizar/${id}`, diario );
  }

  // Deletar um Lancamento
  deleteLancamento( id: number ) : Observable< Object > {
    return this.http.delete< Diario > ( `http://localhost:8080/api/diario/deletar/${id}` );
  }

  // Listar por Período
  getLancamentosPorPeriodo(startDate: string, endDate: string): Observable< Diario[] > {

    return this.http.get< Diario[] >( `http://localhost:8080/api/diario/datas`, { 
      params: { startDate, endDate } 
    });//?startDate=${startDate}&endDate=${endDate}` );
    
  }

}
