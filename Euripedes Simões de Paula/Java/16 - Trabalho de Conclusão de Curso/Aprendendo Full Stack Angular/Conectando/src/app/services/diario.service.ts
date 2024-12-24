import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Diario } from '../model/Diario';

@Injectable({
  providedIn: 'root'
})
export class DiarioService {

  constructor(private http : HttpClient) { }

  // Método para configuração de cabeçalho com token
  private getHttpOptions() : { headers : HttpHeaders } {

  if ( typeof window === 'undefined' ) {
    globalThis.localStorage = {
      getItem: () => null,
      setItem: () => {},
      removeItem: () => {},
      clear: () => {},
      key: (index: number) => null,
      length: 0
    } as Storage;
  }

  const token = localStorage.getItem('token');
  console.log('Token', token);
  const headers = token 
    ? new HttpHeaders( { Authorization : `Bearer ${token}` } )
    : new HttpHeaders();

    return { headers };
  }

  // Lista de todos os Lançamentos
  getLancamentos() : Observable< Diario[] > {
    return this.http.get< Diario[] > ( 'http://localhost:8080/api/diario/transacoes', this.getHttpOptions() )
      .pipe(
        catchError(error => {
          console.error("Erro ao buscar lançamentos:", error);
          return throwError(error);
        })
      );
  }

  getLancamentoById( id: number ) : Observable < Diario > {
    return this.http.get< Diario > ( `http://localhost:8080/api/diario/transacoes/${id}`, this.getHttpOptions() );
  }

  // Criar um Lançamento
  createLancamento( diario : Diario ) : Observable< Diario > {
    return this.http.post< Diario > ( 'http://localhost:8080/api/diario/registrar', diario, this.getHttpOptions() );
  }

  // Atualizar um Lançamento
  updateLancamento(id: number, diario: Diario) : Observable< Diario > {
    return this.http.put< Diario > ( `http://localhost:8080/api/diario/atualizar/${id}`, diario, this.getHttpOptions() );
  }

  // Deletar um Lancamento
  deleteLancamento( id: number ) : Observable< Object > {
    return this.http.delete< Diario > ( `http://localhost:8080/api/diario/deletar/${id}`, this.getHttpOptions() );
  }

  // Listar por Período
  // getLancamentosPorPeriodo(startDate: string, endDate: string): Observable<Diario[]> {

  //   const httpOptions = {
  //       headers: this.getHttpOptions().headers,
  //       params: { startDate, endDate }
        
  //   };

  //   return this.http.get<Diario[]>( `http://localhost:8080/api/diario/datas`, httpOptions )
  //       .pipe(
  //           catchError(error => {
  //               console.error("Erro ao buscar lançamentos por período:", error);
  //               return throwError(error);
  //           })
  //       );
  // }

  getLancamentosPorPeriodo(startDate: string, endDate: string): Observable<Diario[]> {
    return this.http.get<Diario[]>( `http://localhost:8080/diario/datas?startDate=${startDate}&endDate=${endDate}`);
  }

}
