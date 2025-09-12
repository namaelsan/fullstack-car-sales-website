import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatSelectModule } from "@angular/material/select";
import { MatRadioModule } from "@angular/material/radio";
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from "@angular/material/datepicker";
import { Brand } from '../car.models';
import { EditCarDialogComponent } from '../edit-car-dialog/edit-car-dialog.component';
import { BrandService } from '../brand.service';

@Component({
  selector: 'app-edit-brand-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatRadioModule, MatDatepickerModule],
  templateUrl: './edit-brand-dialog.component.html',
  styleUrls: ['./edit-brand-dialog.component.css']
})
export class EditBrandDialogComponent {
  brand: Brand;
  brands: Brand[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: Brand, public dialogRef: MatDialogRef<EditCarDialogComponent>, private brandService: BrandService) {
    this.brand = structuredClone(data);
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
    this.dialogRef.close(this.brand);
  }

  onClose() {
    this.dialogRef.close();
  }

  ngOnInit() {
  }
}
