import { Component, OnInit } from '@angular/core';
import { WebApiObservableService } from '../services/webApiObservable.service';
import { IApplicant, Applicant } from './applicant';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-applicant',
  templateUrl: './applicant.component.html',
  styleUrls: ['./applicant.component.css']
})
export class ApplicantComponent implements OnInit {
  applicant: IApplicant;
  telephonicApplicants: IApplicant[];
  errorMessage: String;
  constructor(private applicantWebService: WebApiObservableService) {

  }

  ngOnInit() {
    this.applicantWebService.createGetService('http://localhost:5050/applicant/telephonic/all')
      .subscribe(result => this.telephonicApplicants = result,
      error => this.errorMessage = <any>error);
    console.log('Inside On Init ' + this.telephonicApplicants);
  }

  onSelectCandidate(applicant: Applicant) {
    console.log('onSelectCandidate' + applicant);
    const url = 'http://localhost:5050/applicant/telephonic/' + applicant.id + '/select';
    this.applicantWebService.createGetService(url).subscribe(
      result => console.log(result),
      error => this.errorMessage = <any>error);
  }

  onRejectCandidate(applicant: Applicant) {
    console.log('onRejectCandidate' + applicant);
    const url = 'http://localhost:5050/applicant/telephonic/' + applicant.id + '/reject';
    this.applicantWebService.createGetService(url).subscribe(
      result => console.log(result),
      error => this.errorMessage = <any>error);
  }

  onSubmitApplicant(applicantForm: NgForm) {
    const value = applicantForm.value;
    this.applicant = new Applicant(value.name, value.phoneNumber, value.email);
    console.log('Inside Component ::::' + this.applicant);

    this.applicantWebService
      .createPostService('http://localhost:5050/applicant/startHireProcess',
      this.applicant)
      .subscribe(
      result => console.log('Result Inside Component ' + result),
      error => this.errorMessage = <any>error
      );
  }

}
