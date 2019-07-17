import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

    loggedIn = false;

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

