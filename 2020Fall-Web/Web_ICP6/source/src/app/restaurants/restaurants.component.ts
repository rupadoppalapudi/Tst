import { RestaurantsService } from './../services/restaurants.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['../app.component.css'],
})
export class RestaurantsComponent implements OnInit {
  public restaurantsList = [];
  public cityName: string;
  public locationSearched = '';
  constructor(private restaurantService: RestaurantsService) {}

  ngOnInit() {}

  getRestaurants() {
    this.restaurantsList = [];
    this.restaurantService.getRestaurants(this.cityName).subscribe((data) => {
      const response = data[`response`];
      const items = response[`groups`][0][`items`];
      items.map((item) => {
        const venueObj = {
          id: item[`venue`][`id`],
          name: item[`venue`][`name`],
          address: {
            street: item[`venue`][`location`][`address`],
            city: item[`venue`][`location`][`city`],
            state: item[`venue`][`location`][`state`],
            postalCode: item[`venue`][`location`][`postalCode`],
            country: item[`venue`][`location`][`country`],
          },
          category: item[`venue`][`categories`][0][`name`],
        };
        this.restaurantsList.push(venueObj);
      });
      this.locationSearched = response[`geocode`][`displayString`];
    });
  }
}
