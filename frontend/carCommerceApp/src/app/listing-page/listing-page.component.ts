import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarService } from '../car.service';
import { Car } from '../car.models';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { EditCarDialogComponent } from '../edit-car-dialog/edit-car-dialog.component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { Page } from '../page.models';
import { Observable } from 'rxjs';
import { CartService } from '../cart.service';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';
import { CarSearchCriteria } from '../car-search-criteria.models';
import { SearchModel } from '../search-model.models';
import { SearchRequest } from '../search-request.models';
import { SearchService } from '../search-service.service';

@Component({
  selector: 'app-listing-page',
  standalone: true,
  imports: [CommonModule, MatDialogModule],
  templateUrl: './listing-page.component.html',
  styleUrls: ['./listing-page.component.css']
})
export class ListingPageComponent {

  constructor(private carservice: CarService, private dialog: MatDialog, public cartService: CartService, public searchService: SearchService) { }

  cars: Car[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  totalElements: number = 0;
  

  totalCartPrice(): number {
    let total = 0;
    let cartIds: number[] = this.cartService.getCart();
    let car;
    cartIds.forEach((id) => {
      car = this.getById(id);
      if (car !== undefined){
        total += car.price;
      }
    })
    return total;
  }

  totalCartElements(): number {
    return this.cartService.getTotalElements();
  }

  clearCart() {
    this.cartService.clearCart();
  }


  openEditCarDialog(car: Car) {
    let dialogRef = this.dialog.open(EditCarDialogComponent, { data: car });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.carservice.updateCarSale(result).subscribe({
          next: (updatedCar) => {
            this.ngOnInit();
          },
          error: (e) => {
            console.error("Error updating car", e)
          }
        });
      }
    });
  }

  openDeleteCarDialog(car: Car) {
    let dialogRef = this.dialog.open(DeleteDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === "yes") {
        this.carservice.deleteCarSale(car.id).subscribe({
          next: (data) => {
            console.log("Car successfully deleted")
            this.ngOnInit();
          }
          ,
          error: (e) => {
            console.error("Error deleting car: ", e)
          }
        });
      }
    }
    )
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

  ngOnInit() {
    const savedPage = localStorage.getItem('currentPage');
    this.currentPage = savedPage ? +savedPage : 1;
    this.loadCars(this.currentPage)
  }

  loadCarsVariables(carPage: Page) {
    this.cars = carPage.content;
    this.totalElements = carPage.totalElements;
    this.totalPages = carPage.totalPages;
  }

  getById(id: number): Car | undefined {
    return this.cars.find((c) => {
        return c.id === id
    })
  }
}
