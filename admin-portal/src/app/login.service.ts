import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class LoginService {

  constructor(private _http: Http) { }

  sendCredential(username: String, password: String) {

    let url = "http://localhost:5050/index";
    let params: String = `username=${username}&password=${password}`;
    let headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded'
    });
    console.log('params' + params);
    return this._http.post(url, params, { headers: headers, withCredentials: true });
  }

  logout() {
    let url = "http://localhost:5050/logout";
    return this._http.get(url, { withCredentials: true });
  }
}
