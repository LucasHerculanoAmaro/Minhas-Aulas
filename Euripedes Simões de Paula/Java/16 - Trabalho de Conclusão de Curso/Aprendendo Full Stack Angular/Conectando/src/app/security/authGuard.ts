import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot, } from "@angular/router";
import { AuthService } from "../services/authService";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class AuthGuard implements CanActivate {
    
constructor( private authService : AuthService, private router : Router ) {}

    canActivate(route : ActivatedRouteSnapshot, state : RouterStateSnapshot) 
        : Observable<boolean> | Promise<boolean> | boolean {

        // const token = localStorage.getItem('Token');
        if (this.authService.isAuthenticated()) {
            return true;
        }

        this.router.navigate(['/login']);
        return false;
        
    }

}