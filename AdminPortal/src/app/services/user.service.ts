import { User } from '../user-account/user';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class UserService {

  constructor(private _http: Http) { }


  getUsers(): Observable<User[]> {
    let url = "http://localhost:5050/api/user/all";
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }


  getPrimaryTransactionList(userName: String) {
    let url = "http://localhost:5050/api/user/primary/transaction?username=" + userName;
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }

  getSavingsTransactionList(userName: String) {
    let url = "http://localhost:5050/api/user/savings/transaction?username=" + userName;
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }

  enableUser(userName: String) {
    console.log('Inside Enable User Service');
    let url = "http://localhost:5050/api/user/" + userName + "/enable";
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }

  disableUser(userName: String) {
    console.log('Inside disable User Service');
    let url = "http://localhost:5050/api/user/" + userName + "/disable";
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }
}
