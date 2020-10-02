import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RecipesService {
  private APP_ID = environment.APP_ID;
  private APP_KEY = environment.APP_KEY;
  private url = `https://api.edamam.com/search?app_id=${this.APP_ID}&app_key=${this.APP_KEY}`;
  constructor(private http: HttpClient) {}

  getRecipeInfo(itemName: string) {
    const url = this.url + `&q=${itemName}`;
    return this.http.get(url);
  }
}
