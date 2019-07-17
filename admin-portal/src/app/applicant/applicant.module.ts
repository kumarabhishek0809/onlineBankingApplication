import { NavbarComponent } from '../navbar/navbar.component';
import { WebApiObservableService } from '../services/webApiObservable.service';
import { CommonModule } from '@angular/common';
import { ApplicantRoutingModule } from './applicant-routing.module';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ApplicantComponent } from './applicant.component';
import { NgModule } from '@angular/core';

@NgModule({
    declarations: [
        ApplicantComponent
    ],
    providers: [],
    imports: [
        CommonModule, FormsModule, ApplicantRoutingModule
    ]
}
)
export class ApplicantModule { }