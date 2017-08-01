import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Rx';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {


    constructor(private authService: AuthService, private router: Router) { }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
        Observable<boolean> |
        Promise<boolean> | boolean {
        this.authService.isAuthenticated()
            .then(
            (authenticated: boolean) => {
                console.log('Inside Guard Service authenticated ::::' + authenticated);
                if (authenticated) {
                    this.router.routerState;
                    return true;
                } else {
                    this.router.navigate(['/']);
                };
            });
        return false;
    }
}
