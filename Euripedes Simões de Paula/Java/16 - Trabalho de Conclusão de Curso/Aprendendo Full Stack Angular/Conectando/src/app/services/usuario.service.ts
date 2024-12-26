import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../model/Usuario";

@Injectable({
    providedIn: 'root'
})

export class UsuarioService {

    constructor ( private http : HttpClient ) {}

    login( usuario : Usuario ) : Observable<Object> {
        return this.http.post('http://localhost:8080/api/usuarios/logar', usuario);
    }

}