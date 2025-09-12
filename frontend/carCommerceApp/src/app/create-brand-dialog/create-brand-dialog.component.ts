import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { Brand } from '../car.models';
import { BrandService } from '../brand.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-brand-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatDialogModule],
  templateUrl: './create-brand-dialog.component.html',
  styleUrls: ['./create-brand-dialog.component.css']
})
export class CreateBrandDialogComponent {
    brand: Brand;
    brands: Brand[] = [];
  
    constructor(public dialogRef: MatDialogRef<CreateBrandDialogComponent>, private brandService: BrandService) {
      this.brand = {
        bname: "",
      };
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
