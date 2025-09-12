import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopBarComponent } from "../top-bar/top-bar.component";
import { BrandService } from '../brand.service';
import { DialogRef } from '@angular/cdk/dialog';
import { CreateBrandDialogComponent } from '../create-brand-dialog/create-brand-dialog.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Brand } from '../car.models';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { EditBrandDialogComponent } from '../edit-brand-dialog/edit-brand-dialog.component';

@Component({
  selector: 'app-brands-page',
  standalone: true,
  imports: [CommonModule, TopBarComponent, MatDialogModule],
  templateUrl: './brands-page.component.html',
  styleUrls: ['./brands-page.component.css']
})
export class BrandsPageComponent implements OnInit {

  brands: Brand[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  totalElements: number = 0;
  freshPage: boolean = true;

  constructor(private brandService: BrandService, private dialog: MatDialog) {

  }

  openCreateBrandDialog() {
    let dialogRef = this.dialog.open(CreateBrandDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.brandService.createBrand(result).subscribe({
          next: (newBrand) => {
            this.ngOnInit();
          },
          error: (e) => {
            console.error("Error creating brand", e)
          }
        });
      }
    });
  }

  openDeleteBrandDialog(brand: Brand) {
    let dialogRef = this.dialog.open(DeleteDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === "yes") {
        this.brandService.deleteBrand(brand.id!).subscribe({
          next: (data) => {
            console.log("Brand successfully deleted")
            this.ngOnInit();
          }
          ,
          error: (e) => {
            console.error("Error deleting brand: ", e)
          }
        });
      }
    }
    )
  }

  openEditBrandDialog(brand: Brand) {
    let dialogRef = this.dialog.open(EditBrandDialogComponent, { data: brand });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.brandService.updateBrand(result).subscribe({
          next: (updatedBrand) => {
            this.ngOnInit();
          },
          error: (e) => {
            console.error("Error updating brand", e)
          }
        });
      }
    });
  }

  goToPage(nextPage: number) {
    if (0 < nextPage && nextPage <= this.totalPages)
      this.currentPage = nextPage;
    localStorage.setItem('currentPage', nextPage.toString())
    window.location.reload();
  }

  ngOnInit() {
    // this.loadPageIndex();
    this.loadBrands(this.currentPage);
  }

  // loadPageIndex() {
  //   const savedPage = localStorage.getItem('currentPage');
  //   this.currentPage = savedPage ? +savedPage : 1;
  // }

  loadBrands(pageNum: number) {
    this.brandService.getAllBrands().subscribe({
      next: (data) => {
        this.loadBrandsVariables(data);
      }
      ,
      error: (e) => {
        console.error("Error getting car sales: ", e)
      }
    })
  }

  loadBrandsVariables(brands: Brand[]) {
      this.brands = structuredClone(brands);
      this.totalElements = brands.length;
    }
}
