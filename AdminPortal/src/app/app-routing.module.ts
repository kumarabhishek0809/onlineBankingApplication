import { SavingstransactionComponent } from './savingstransaction/savingstransaction.component';
import { PrimarytransactionComponent } from './primarytransaction/primarytransaction.component';
import { UserAccountComponent } from './user-account/user-account.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ShoppingRecipeComponent } from './shopping-recipe/shopping-recipe.component';
import {AppointmentComponent} from './appointment/appointment.component';

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
}

]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
