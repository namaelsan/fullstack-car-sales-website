import { Component, inject, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarService } from '../car.service';
import { Car } from '../car.models';
import { MatDialog, MatDialogConfig, MatDialogModule } from '@angular/material/dialog';
import { EditCarDialogComponent } from '../edit-car-dialog/edit-car-dialog.component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { Page } from '../page.models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-listing-page',
  standalone: true,
  imports: [CommonModule, MatDialogModule, EditCarDialogComponent],
  templateUrl: './listing-page.component.html',
  styleUrls: ['./listing-page.component.css']
})
export class ListingPageComponent {

  constructor(private carservice: CarService, public dialog: MatDialog) { }

  currentPage: number = 1;
  cars: Car[] = [];
  totalPages: number = 0;
  totalElements: number = 0;

  totalPrice(): number {
    let total: number = 0;
    for (let i = 0; i < this.cars.length; i++) {
      total += this.cars[i].price;
    }
    return total;
  }

  openEditCarDialog(car: Car) {
    let dialogRef = this.dialog.open(EditCarDialogComponent, { data: car });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.carservice.updateCarSale(result).subscribe({
          next: (updatedCar) => {
            var oldCar = this.cars.find(c => c.id === updatedCar.id);
            if (oldCar) {
              oldCar = updatedCar;
            }
          },
          error: (e) => {
            console.error("Error updating car", e)
          }
        });
      }
    }
    )
  }

  openDeleteCarDialog() {
    let dialogRef = this.dialog.open(DeleteDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === "yes") {
        this.carservice.deleteCarSale(result);
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
