import { TelephonicInterviewComponent } from '../telephonic-interview/telephonic-interview.component';
import { ApplicantComponent } from './applicant.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const applicantRoute: Routes = [{

    path: 'applicant',
    component: ApplicantComponent,
    children: [
        {
            path: 'telephonicInterview',
            component: TelephonicInterviewComponent
        }
    ]

}];
@NgModule({
    imports: [
        RouterModule.forChild(applicantRoute)
    ],
    exports: [RouterModule]
})
export class ApplicantRoutingModule {

}