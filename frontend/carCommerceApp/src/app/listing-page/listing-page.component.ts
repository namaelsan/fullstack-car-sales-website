import { Component, inject, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarService } from '../car.service';
import { Car } from '../car.models';
import { MatDialog, MatDialogConfig, MatDialogModule } from '@angular/material/dialog';
import { EditCarDialogComponent } from '../edit-car-dialog/edit-car-dialog.component';

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
  totalPages: number = 2;

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
          next: (data) => {
            var carNew = this.cars.find(c => c.id === data.id);
            if (carNew) {
              carNew = structuredClone(data);
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

  goToPage(nextPage: number) {
    if (0 < nextPage && nextPage <= this.totalPages)
      this.currentPage = nextPage;
    localStorage.setItem('currentPage', nextPage.toString())
    window.location.reload();
  }

  loadCars(page: number) {
    this.carservice.getCarSalesByPage(page).subscribe({
      next: (data) => {
        this.cars = data;
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
}
