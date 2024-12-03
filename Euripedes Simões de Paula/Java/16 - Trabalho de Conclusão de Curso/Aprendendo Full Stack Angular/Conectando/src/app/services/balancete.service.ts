import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Balancete } from '../model/Balancete';

@Injectable({
  providedIn: 'root'
})
export class BalanceteService {

  constructor( private http : HttpClient ) { }

  getBalancete() : Observable< Balancete[] > {
    return this.http.get< Balancete[] > ( 'AINDA INDEFINIDO' );
  }

}
