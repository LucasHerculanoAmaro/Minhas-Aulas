import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../services/authService';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor( private router : Router, private authService : AuthService ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem('token'); // O token já está implementado

    if (token) {
      const cloned = req.clone ({
        setHeaders : { Authorization : `Bearer ${token}` },
      });
      // console.log('Token adicionado ao header:', token);
      return next.handle(cloned); // Linha adicionada

    }

    return next.handle(req).pipe(
      catchError(error => {
        if (error.status === 401 || error.status === 403) {
          localStorage.removeItem('token');
          this.router.navigate(['/login']);
        }
        return throwError(error);
      })
    )

  }

}
