import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RestaurantsService {
  private CLIENT_ID = environment.CLIENT_ID;
  private CLIENT_SECRET = environment.CLIENT_SECRET_KEY;
  private url = `https://api.foursquare.com/v2/venues/explore?v=20180323&client_id=${this.CLIENT_ID}&client_secret=${this.CLIENT_SECRET}&categoryId=4d4b7105d754a06374d81259`;
  constructor(private http: HttpClient) {}

  getRestaurants(cityName: string) {
    const url = this.url + `&near=${cityName}`;
    return this.http.get(url);
  }

  getRestaurantByItem(itemName: string, cityName: string) {
    const url = this.url + `&near=${cityName}`;
    return this.http.get(url);
  }
}
