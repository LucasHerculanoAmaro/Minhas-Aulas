import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Razao } from '../model/Razao';

@Injectable({
  providedIn: 'root'
})
export class RazaoService {

  constructor( private http : HttpClient ) { }

  getRazao() : Observable< Razao[] > {
    return this.http.get< Razao[] > ( '' )
  }

}
