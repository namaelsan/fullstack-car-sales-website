import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Brand, Car } from '../car.models';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { importProvidersFrom, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from "@angular/material/dialog";
import { MatRadioModule } from '@angular/material/radio';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { BrandService } from '../brand.service';

@Component({
  selector: 'app-create-car-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, FormsModule, MatRadioModule, MatFormFieldModule, MatInputModule, MatDatepickerModule, MatNativeDateModule, MatSelectModule],
  templateUrl: './create-car-dialog.component.html',
  styleUrls: ['./create-car-dialog.component.css']
})
export class CreateCarDialogComponent {
  car: Car = {
    id: -1,
    brand: {
      id: -1,
      bname: "",
    },
    specification: "",
    litre: 0,
    price: 0,
    releaseDateTime: new Date(),
    used: true,
  };

  brands: Brand[] = [];

  constructor(brandService: BrandService ,public dialogRef: MatDialogRef<CreateCarDialogComponent>) {
    brandService.getAllBrands().subscribe({
      next: (brands) => {
        this.brands = brands;
      },
      error: (e) => {
        console.error("Error gettings brands", e)
        this.brands = [];
      }
    });
  }

  onSave() {
    this.dialogRef.close(this.car);
  }

  onClose() {
    this.dialogRef.close();
  }

  ngOnInit() {
  }
}
