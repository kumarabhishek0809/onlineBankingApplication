import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {

  constructor(private _http: Http) { }

  sendCredential(username: String, password: String) {
    let url = "http://localhost:8080/index";
    let params = `username${username}&password${password}`;
    let headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded'
    });
    return this._http.post(url, params, { headers: headers, withCredentials: true });
  }
}
