import { IApplicant } from '../applicant/applicant';


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
  constructor(private router: Router) { }

  ngOnInit() {

  }

  getApplicants(): IApplicant[] {
    return this.applicants;
  }


}
