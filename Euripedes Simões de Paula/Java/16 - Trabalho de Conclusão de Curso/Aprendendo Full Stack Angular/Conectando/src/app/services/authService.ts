import { HttpClient } from "@angular/common/http";
import { UsuarioLogin } from "../model/Login";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
  })
export class AuthService {

    constructor(private http : HttpClient) {}

    login(usuario : string, senha : string): Observable<UsuarioLogin> {
        return this.http.post<UsuarioLogin>('http://localhost:8080/api/usuarios/logar', { usuario, senha });
    }

    storeToken(token : string) : void {
        localStorage.setItem('token', token);
    }

    getToken() : string | null {
        return localStorage.getItem('token');
    }

    isAuthenticated() : boolean {
        return this.getToken() !== null;
    }

    logout() : void {
        localStorage.removeItem('token');
    }

    

}