import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Car } from '../car.models';
import { CarService } from '../car.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CartService } from '../cart.service';
import { SearchService } from '../search.service';
import { CarDataService } from '../car-data-service.service';
import { EditCarDialogComponent } from '../edit-car-dialog/edit-car-dialog.component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';
import { Page } from '../page.models';
import { CreateCarDialogComponent } from '../create-car-dialog/create-car-dialog.component';
import { TopBarComponent } from '../top-bar/top-bar.component';
import * as moment from 'moment';

@Component({
  selector: 'app-cars-page',
  standalone: true,
  imports: [CommonModule, MatDialogModule, TopBarComponent],
  templateUrl: './cars-page.component.html',
  styleUrls: ['./cars-page.component.css']
})
export class CarsPageComponent {

  constructor(private carservice: CarService, private dialog: MatDialog, public cartService: CartService, public searchService: SearchService, private carDataService: CarDataService) { }

  cars: Car[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  totalElements: number = 0;
  freshPage: boolean = true;


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

  openCreateCarDialog() {
    let dialogRef = this.dialog.open(CreateCarDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.carservice.createCarSale(result).subscribe({
          next: (newCar) => {
            this.ngOnInit();
          },
          error: (e) => {
            console.error("Error creating car", e)
          }
        });
      }
    });
  }

  formatDate(date: Date) {
    return moment(date).format('MMMM Do YYYY, h:mm:ss a');
  }
  
}
