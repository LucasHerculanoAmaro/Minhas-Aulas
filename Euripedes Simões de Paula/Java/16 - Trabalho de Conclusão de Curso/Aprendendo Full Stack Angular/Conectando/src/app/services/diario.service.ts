import { HttpClient } from '@angular/common/http';
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

  // Criar um Lançamento
  createLancamento( diario : Diario ) : Observable< Diario > {
    return this.http.post< Diario > ( 'http://localhost:8080/api/diario/registrar', diario );
  }

  // Atualizar um Lançamento
  updateLancamento( diario : Diario ) : Observable< Diario > {
    return this.http.put< Diario > ('http://localhost:8080/api/diario/atuaizar', diario);
  }

  // Deletar um Lancamento
  deleteLancamento( id: number ) {
    return this.http.delete< Diario > ( 'htp://localhost:8080/api/diario/deletar/${id}' )
  }

}
