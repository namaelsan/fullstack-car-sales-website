import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Car } from '../car.models';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormBuilder, FormsModule } from '@angular/forms';
import { CarService } from '../car.service';

@Component({
  selector: 'app-edit-car-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, FormsModule, MatFormFieldModule],
  templateUrl: './edit-car-dialog.component.html',
  styleUrls: ['./edit-car-dialog.component.css']
})
export class EditCarDialogComponent {
  car: Car
  

  constructor(private carService: CarService,
    private dialogRef: MatDialogRef<EditCarDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data: Car) {
    this.car = { ...data}
  }

  save() {
    this.carService.updateCarSale(this.car);
    this.dialogRef.close();
  }

  close() {
    this.dialogRef.close();
  }

}

