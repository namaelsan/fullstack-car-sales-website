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

@Component({
  selector: 'app-listing-page',
  standalone: true,
  imports: [CommonModule, MatDialogModule],
  templateUrl: './listing-page.component.html',
  styleUrls: ['./listing-page.component.css']
})
export class ListingPageComponent {

  constructor(private carservice: CarService, private dialog: MatDialog, public cartService: CartService) { }

  currentPage: number = 1;
  cars: Car[] = [];
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


  openEditCarDialog(car: Car) {
    let dialogRef = this.dialog.open(EditCarDialogComponent, { data: car });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.carservice.updateCarSale(result).subscribe({
          next: (updatedCar) => {
            const index = this.cars.findIndex(c => c.id === updatedCar.id);
            if (index !== -1) {
              this.cars[index] = updatedCar;
              this.cartService.updateCart(updatedCar);
            }
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
          }
          ,
          error: (e) => {
            console.error("Error deleting car: ", e)
          }
        });
        const index = this.cars.findIndex(c => c.id === car.id);
        if (index !== -1) {
          this.cars.splice(index,1);
        }
      }
    }
    )
  }

  openAddToCartDialog(car: Car) {
    let dialogRef = this.dialog.open(AddToCartDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === "yes") {
        try {
          this.cartService.addToCart(car);          
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
    this.carservice.getCarSalesByPage(pageNum).subscribe({
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
    this.cars =  carPage.content;
    this.totalElements = carPage.totalElements
    this.totalPages = carPage.totalPages
  }
}
