import { AuthService } from '../auth.service';
import { Component, OnInit } from '@angular/core';
import { Form } from '@angular/forms';
import { NgModule } from '@angular/core';


@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  constructor(private authService: AuthService) { }

  onSubmitApplicant(form: Form) {
  }

  ngOnInit() {
  }

  onLogin() {
    this.authService.login();
  }
  onLogout() {
    this.authService.logOut();
  }
}
