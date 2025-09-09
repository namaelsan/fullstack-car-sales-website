import { Component, importProvidersFrom, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Car } from '../car.models';
import { MatRadioModule } from '@angular/material/radio';

import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';


@Component({
  selector: 'app-edit-car-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, FormsModule, MatRadioModule, MatFormFieldModule, MatInputModule, MatDatepickerModule, MatNativeDateModule],
  templateUrl: './edit-car-dialog.component.html',
  styleUrls: ['./edit-car-dialog.component.css']
})
export class EditCarDialogComponent implements OnInit {
  car: Car;
  constructor (@Inject(MAT_DIALOG_DATA) public data: Car, public dialogRef: MatDialogRef<EditCarDialogComponent>) {
    this.car = structuredClone(data)
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
