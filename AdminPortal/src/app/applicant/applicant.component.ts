import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives';
import { WebApiObservableService } from '../services/webApiObservable.service';
import { IApplicant, Applicant } from './applicant';

@Component({
  selector: 'app-applicant',
  templateUrl: './applicant.component.html',
  styleUrls: ['./applicant.component.css']
})
export class ApplicantComponent implements OnInit {
  applicant: IApplicant;
  errorMessage: String;
  constructor(private applicantWebService: WebApiObservableService) {

  }

  ngOnInit() {
  }

  onSubmitApplicant(applicantForm: NgForm) {
    const value = applicantForm.value;
    this.applicant = new Applicant(value.name, value.phoneNumber, value.email);
    console.log(this.applicant);

    this.applicantWebService
      .createService('http://localhost:5050/applicant/startHireProcess', this.applicant)
      .subscribe(
      result => console.log(result),
      error => this.errorMessage = <any>error
      );
  }

}
