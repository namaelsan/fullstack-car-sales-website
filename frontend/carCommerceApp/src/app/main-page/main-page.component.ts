import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarService } from '../car.service';
import { Car } from '../car.models';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Page } from '../page.models';
import { CartService } from '../cart.service';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';
import { SearchService } from '../search.service';
import { TopBarComponent } from "../top-bar/top-bar.component";
import { CarDataService } from '../car-data.service';
import * as moment from 'moment';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-listing-page',
  standalone: true,
  imports: [CommonModule, MatDialogModule, TopBarComponent, RouterLink],
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent {

  constructor(private carservice: CarService, private dialog: MatDialog, public cartService: CartService, public searchService: SearchService, private carDataService: CarDataService) { }

  cars: Car[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  totalElements: number = 0;


  totalCartPrice(): number {
    return this.cartService.getTotalPrice();
  }

  totalCartElements(): number {
    return this.cartService.getTotalElements();
  }

  clearCart() {
    this.cartService.clearCart();
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

  goToPage(nextPage: number) {
    if (0 < nextPage && nextPage <= this.totalPages)
      this.currentPage = nextPage;
    localStorage.setItem('currentPage', nextPage.toString())
    window.location.reload();
  }

  ngOnInit() {
    this.loadPageIndex();
    this.loadCars(this.currentPage)
  }

  loadCars(pageNum: number) {
    this.searchService.searchCars().subscribe({
      next: (data) => {
        this.loadCarsVariables(data);
      }
      ,
      error: (e) => {
        console.error("Error getting car sales: ", e)
      }
    })
  }

  loadCarsVariables(carPage: Page<Car>) {
    this.carDataService.setCarsPage(carPage);
    this.carDataService.carsPage$.subscribe(page => {
      this.cars = page.content;
      this.totalPages = page.totalPages;
      this.totalElements = page.totalElements;
    })
  }

  loadPageIndex() {
    const savedPage = localStorage.getItem('currentPage');
    this.currentPage = savedPage ? +savedPage : 1;
  }

  formatDate(date: Date) {
    return moment(date).format('MMMM Do YYYY, h:mm:ss a');
  }
}
