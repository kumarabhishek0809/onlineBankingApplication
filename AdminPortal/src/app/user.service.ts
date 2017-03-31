import { Http } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  constructor(private _http: Http) { }

  getUsers() {
    let url = "http://localhost:8080/api/user/all";
    return this._http.get(url, { withCredentials: true });
  }

  getPrimaryTransactionList(userName: String) {
    let url = "http://localhost:8080/api/user/primary/transaction?userName" + userName;
    return this._http.get(url, { withCredentials: true });
  }

  getSavingsTransactionList(userName: String) {
    let url = "http://localhost:8080/api/user/savings/transaction?userName" + userName;
    return this._http.get(url, { withCredentials: true });
  }

  enableUser(userName: String) {
    let url = "http://localhost:8080/api/user/" + userName + "/enable";
    return this._http.get(url, { withCredentials: true });
  }

  disableUser(userName: String) {
    let url = "http://localhost:8080/api/user/" + userName + "/disable";
    return this._http.get(url, { withCredentials: true });
  }
}
