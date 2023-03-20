import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export let token: String = ""

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {
        token = localStorage.getItem("token")??""
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        if (localStorage.getItem("token")) {
            return true
        }
        this.router.navigateByUrl("/login")
        return false
    }
}