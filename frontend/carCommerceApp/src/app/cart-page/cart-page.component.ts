import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';
import { Page } from '../page.models';
import { Car } from '../car.models';
import * as moment from 'moment';
import { CarService } from '../car.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CartService } from '../cart.service';
import { SearchService } from '../search.service';
import { CarDataService } from '../car-data.service';
import { TopBarComponent } from "../top-bar/top-bar.component";
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [CommonModule, TopBarComponent, MatDialogModule],
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent {
  constructor(private carservice: CarService, private dialog: MatDialog, public cartService: CartService, public searchService: SearchService, private carDataService: CarDataService) { }

  cars: Car[] = [];


  totalCartPrice(): number {
    return this.cartService.getTotalPrice();
  }

  totalCartElements(): number {
    return this.cartService.getTotalElements();
  }

  clearCart() {
    this.cartService.clearCart();
    this.ngOnInit();
  }

  openAddToCartDialog(id: number) {
    let dialogRef = this.dialog.open(AddToCartDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === "yes") {
        try {
          this.cartService.addToCart(id);
        } catch (error) {
          console.log(error)
        }
      }
    }
    )
  }
  openDeleteCartDialog(car: Car) {
    let dialogRef = this.dialog.open(DeleteDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === "yes") {
        this.cartService.removeFromCart(car.id)
        console.log("Car successfully deleted")
        this.ngOnInit();
      }
    });
  }

  ngOnInit() {
    this.loadCars()
  }

  loadCars() {
    let carIds = this.cartService.getCart()
    let cars: Car[] = [];
    carIds.forEach(id => {
      this.carservice.getCarById(id).subscribe({
        next: (car) => {
          cars.push(car);
        },
        error: (e) => {
          console.error("Error getting car", e)
        }
      })

    })
    let carPage = this.carArrayToPage(cars);
    this.loadCarsVariables(carPage);
  }


  carArrayToPage(cars: Car[]): Page<Car> {
    let carPage = {
      empty: false,
      content: cars,
      totalElements: cars.length,
      totalPages: 1,
    }

    return carPage;
  }

  loadCarsVariables(carPage: Page<Car>) {
    this.carDataService.setCarsPage(carPage);
    this.carDataService.carsPage$.subscribe(page => {
      this.cars = page.content;
    })
  }

  formatDate(date: Date) {
    return moment(date).format('MMMM Do YYYY, h:mm:ss a');
  }
}
