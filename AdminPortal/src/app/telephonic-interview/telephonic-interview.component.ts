import { IApplicant } from '../applicant/applicant';
import { ApplicantService } from '../services/applicant.service';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-telephonic-interview',
  templateUrl: './telephonic-interview.component.html',
  styleUrls: ['./telephonic-interview.component.css']
})
export class TelephonicInterviewComponent implements OnInit {

  private applicants: IApplicant[];
  private errorMessage: String;
  constructor(private router: Router,
    private applicantService: ApplicantService) { }

  ngOnInit() {
    this.applicantService.getTelephonicCandidates()
      .subscribe(
      responseApplicants => this.applicants = responseApplicants,
      responseError => this.errorMessage = responseError);

  }

  getApplicants(): IApplicant[] {
    return this.applicants;
  }
  onSelectTelephonic(applicant: IApplicant) {
    this.applicantService.selectApplicant(applicant).subscribe();
    location.reload();
  }

  onRejectTelephonic(applicant: IApplicant) {
    this.applicantService.rejectApplicant(applicant).subscribe();
    location.reload();
  }

}
