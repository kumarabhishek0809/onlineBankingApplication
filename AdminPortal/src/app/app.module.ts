import { ShoppingListService } from './shopping-list/shopping-list.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
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
import { RecipesComponent } from './recipes/recipes.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import { RecipeListComponent } from './recipes/recipe-list/recipe-list.component';
import { RecipeItemComponent } from './recipes/recipe-list/recipe-item/recipe-item.component';
import { ShoppingEditComponent } from './shopping-list/shopping-edit/shopping-edit.component';
import { ShoppingRecipeComponent } from './shopping-recipe/shopping-recipe.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { BetterHighlightDirective } from './shared/better-highlight.directive';
import { ApplicantComponent } from './applicant/applicant.component';
import { TelephonicInterviewComponent } from './telephonic-interview/telephonic-interview.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    UserAccountComponent,
    PrimarytransactionComponent,
    SavingstransactionComponent,

    ShoppingListComponent,
    RecipesComponent,
    RecipeDetailComponent,
    RecipeListComponent,
    RecipeItemComponent,
    ShoppingEditComponent,
    ShoppingRecipeComponent,
    AppointmentComponent,
    BetterHighlightDirective,
    ApplicantComponent,
    TelephonicInterviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [LoginService,
    UserService,
    WebApiObservableService,
    ShoppingListService],
  bootstrap: [AppComponent]
})
export class AppModule { }
