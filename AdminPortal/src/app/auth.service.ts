import { Injectable } from '@angular/core';
import { setTimeout } from 'timers';

@Injectable()
export class AuthService {

    loggedIn = true;

    isAuthenticated() {
        const promise = new Promise((resolve, reject) => {
            console.log('Inside Auth Service');
            setTimeout(() => resolve(this.loggedIn), 800);
        });
        return promise;
    }

    login() {
        this.loggedIn = true;
    }
    logOut() {
        this.loggedIn = false;
    }
}

