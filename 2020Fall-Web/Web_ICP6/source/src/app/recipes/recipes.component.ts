import { RestaurantsService } from './../services/restaurants.service';
import { RecipesService } from './../services/recipes.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['../app.component.css'],
})
export class RecipesComponent implements OnInit {
  public itemName: string;
  public recipesList = [];
  public cityName = '';
  constructor(
    private recipeService: RecipesService,
  ) {}

  ngOnInit() {}

  getRecipeDetails() {
    this.recipesList = [];
    this.recipeService.getRecipeInfo(this.itemName).subscribe((data) => {
      const recipes = data[`hits`];
      recipes.map(recipe => {
        console.log(recipe);
        const recipeObj = {
          name : recipe.recipe.label,
          url : recipe.recipe.url,
          icon : recipe.recipe.image,
          ingredients: recipe.recipe.ingredientLines.slice(0, 4)
        };
        this.recipesList.push(recipeObj);
      });
    });
  }
}
