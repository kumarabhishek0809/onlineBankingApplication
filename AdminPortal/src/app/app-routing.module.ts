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

const appRoutes: Routes = [{
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
    component: AppointmentComponent
},
{ path: 'applicant', component: ApplicantComponent },
{
    path: 'primaryTransaction/:username',
    component: PrimarytransactionComponent
},
{
    path: 'savingsTransaction/:username',
    component: SavingstransactionComponent
},
{
    path: 'shopingCart',
    component: ShoppingRecipeComponent
},
{
    path: 'telephonicInterview', component: TelephonicInterviewComponent
}
]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
