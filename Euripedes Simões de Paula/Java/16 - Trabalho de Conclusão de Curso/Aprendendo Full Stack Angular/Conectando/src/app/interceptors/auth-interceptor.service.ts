import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor( private router : Router ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem('token');

    if (token) {
      req = req.clone ({
        setHeaders : { Authorization : `Bearer ${token}` },
      });
    } else if (!token) {
      this.router.navigate(['/login']);
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
