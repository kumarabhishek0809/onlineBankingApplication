import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams }
  from '@angular/http';
import { Observable } from 'rxjs';

// Observable class extensions
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/throw';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

@Injectable()
export class WebApiObservableService {
  headers: Headers;
  options: RequestOptions;

  constructor(private http: Http) {
    this.headers = new Headers({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    this.options = new RequestOptions({ headers: this.headers });
  }

  createPostService(url: string, param: any): Observable<any> {
    const body = JSON.stringify(param);
    console.log('createService --:' + body);
    return this.http
      .post(url, body, this.options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  createGetService(url: string): Observable<any> {
    console.log('createGetService --:' + url);
    return this.http.get(url)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(response: Response) {
    const body = response.json();
    console.log('Inside Service' + body);
    return body || {};
  }

  private handleError(error: any) {
    const errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}