import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [
    new Recipe('A Test Recipe', 'This is Simple Recipe',
      'http://cdn-image.myrecipes.com/sites/default/files/image/recipes/sl/12/10/sweet-potato-fettuccine-sl-x.jpg'),
    new Recipe('A Test Recipe', 'This is Simple Recipe',
      'http://cdn-image.myrecipes.com/sites/default/files/image/recipes/sl/12/10/sweet-potato-fettuccine-sl-x.jpg')
  ];

  @Output() recipeWasSelected = new EventEmitter<Recipe>();
  constructor() { }

  ngOnInit() {
  }

  onRecipeSelected(recipe: Recipe) {
    console.log(recipe.name);
    this.recipeWasSelected.emit(recipe);
  }
}
