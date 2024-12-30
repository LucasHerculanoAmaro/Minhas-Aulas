import { HttpClient } from "@angular/common/http";
import { UsuarioLogin } from "../model/Login";
import { Observable } from "rxjs";
import { Router } from "@angular/router";

export class AuthService {

    constructor(private http : HttpClient, private router : Router) {}

    logar(login : UsuarioLogin): Observable<UsuarioLogin> {
        return this.http.post<UsuarioLogin>('http://localhost:8080/api/usuarios/logar', UsuarioLogin);
    }

    

}