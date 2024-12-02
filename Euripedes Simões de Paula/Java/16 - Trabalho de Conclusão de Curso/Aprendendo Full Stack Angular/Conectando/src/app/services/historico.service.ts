import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Historico } from "../model/Historico";

@Injectable({
    providedIn: 'root'
  })

export class HistoricoService {

    constructor( private http : HttpClient ){ }

    // Lista de todos os Lançamentos do Histórico
    getHistorico() : Observable< Historico[] > {
        return this.http.get< Historico[] > ( 'http://localhost:8080/api/historico/transacoes' );
    }

    // Apenas um ID de Lançamaneto do Histórico
    getHistoricoById( id : number ) : Observable < Historico > {
        return this.http.get< Historico > ( `http://localhost:8080/api/historico/${id}` );
    } 

}