import { UserAccountComponent } from './user-account/user-account.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';


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
}]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
