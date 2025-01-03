import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../model/Usuario";

@Injectable({
    providedIn: 'root'
})

export class UsuarioService {

    constructor ( private http : HttpClient ) {}

    cadastrar( usuario : Usuario ) : Observable<Usuario> {
        
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

        console.log('token enviado:', token)

        return this.http.post<Usuario>('http://localhost:8080/api/usuarios/cadastrar', usuario, { headers });
    }

    login( usuario : Usuario ) : Observable<Object> {
        return this.http.post('http://localhost:8080/api/usuarios/logar', usuario);
    }

}