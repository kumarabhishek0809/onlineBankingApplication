import { Applicant } from '../applicant/applicant';
import { User } from '../user-account/user';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
@Injectable()
export class ApplicantService {

  constructor(private _http: Http) { }

  selectApplicant(applicant: Applicant) {
    console.log('Inside Enable User Service');
    const url = 'http://localhost:5050/applicant/telephonic/' + applicant.id + '/select';
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }

  rejectApplicant(applicant: Applicant) {
    console.log('Inside Enable User Service');
    const url = 'http://localhost:5050/applicant/telephonic/' + applicant.id + '/reject';
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }

  getTelephonicCandidates(): Observable<Applicant[]> {
    const url = 'http://localhost:5050/applicant/telephonic/all';
    console.log('getTelephonicCandidates');
    return this._http.get(url).map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json() || 'Server'));
  }
}
