import { SharedModule } from './shared/shared.module';
import { ApplicantModule } from './applicant/applicant.module';
import { RecipesModule } from './recipes/recipes.modules';
import { ModelDrivenForm } from './forms/modeldriven/modeldrivenform-page.component';
import { TemplateDrivenForm } from './forms/templatedriven/templatedrivenform-page.component';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth-guard.service';
import { ShoppingListService } from './shopping-list/shopping-list.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginService } from './login.service';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserService } from './services/user.service';
import { WebApiObservableService } from './services/webApiObservable.service';
import { UserAccountComponent } from './user-account/user-account.component';
import { PrimarytransactionComponent } from './primarytransaction/primarytransaction.component';
import { SavingstransactionComponent } from './savingstransaction/savingstransaction.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { ShoppingEditComponent } from './shopping-list/shopping-edit/shopping-edit.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { BetterHighlightDirective } from './shared/better-highlight.directive';
import { TelephonicInterviewComponent } from './telephonic-interview/telephonic-interview.component';
import { WorkappointmentComponent } from './appointment/workappointment/workappointment.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    UserAccountComponent,
    PrimarytransactionComponent,
    SavingstransactionComponent,
    TemplateDrivenForm,
    ModelDrivenForm,
    ShoppingListComponent,
    ShoppingEditComponent,
    AppointmentComponent,
    BetterHighlightDirective,
    TelephonicInterviewComponent,
    WorkappointmentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    ReactiveFormsModule,
    ApplicantModule
    //, RecipesModule
  ],
  providers: [LoginService,
    UserService,
    WebApiObservableService,
    ShoppingListService,
    AuthGuard,
    AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
