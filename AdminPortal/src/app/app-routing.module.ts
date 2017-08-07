import { ModelDrivenForm } from './forms/modeldriven/modeldrivenform-page.component';
import { TemplateDrivenForm } from './forms/templatedriven/templatedrivenform-page.component';
import { AuthGuard } from './auth-guard.service';
import { WorkappointmentComponent } from './appointment/workappointment/workappointment.component';
import { ModuleWithProviders } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApplicantComponent } from './applicant/applicant.component';
import { LoginComponent } from './login/login.component';
import { PrimarytransactionComponent } from './primarytransaction/primarytransaction.component';
import { SavingstransactionComponent } from './savingstransaction/savingstransaction.component';
import { ShoppingRecipeComponent } from './shopping-recipe/shopping-recipe.component';
import { TelephonicInterviewComponent } from './telephonic-interview/telephonic-interview.component';
import { UserAccountComponent } from './user-account/user-account.component';
import { AppointmentComponent } from './appointment/appointment.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'userAccount',
        component: UserAccountComponent
    },
    {
        path: 'appointment',
        component: AppointmentComponent,
        children: [{
            path: 'workappointment',
            component: WorkappointmentComponent
        }]
    },
    {
        path: 'applicant',
        component: ApplicantComponent,
        children: [
            {
                path: 'telephonicInterview',
                component: TelephonicInterviewComponent
            }
        ]
    },
    {
        path: 'primaryTransaction/:username',
        component: PrimarytransactionComponent
    },
    {
        path: 'savingsTransaction/:username',
        canActivate: [AuthGuard],
        component: SavingstransactionComponent
    },
    {
        path: 'shopingCart',
        component: ShoppingRecipeComponent
    },
    {
        path: 'templateDrivenForm',
        component: TemplateDrivenForm
    },
    {
        path: 'modelDrivenForm',
        component: ModelDrivenForm
    },
]


export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
