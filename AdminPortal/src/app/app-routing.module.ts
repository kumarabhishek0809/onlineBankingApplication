import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const appRoutes: Routes = [{
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
}]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);

@NgModule({
    imports: [routing],
    exports: [RouterModule],
    providers: []
})
export class AppRouterModule { }
