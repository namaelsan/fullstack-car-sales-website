import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SearchModel } from '../search-model.models';
import { CarSearchCriteria } from '../car-search-criteria.models';
import { Brand, Car } from '../car.models';
import { BrandService } from '../brand.service';
import { map, Observable } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SearchService } from '../search-service.service';
import { AuthService } from '../auth.service';
import { CarDataService } from '../car-data-service.service';
import { Page } from '../page.models';
import { SortDirection } from '../search-request.models';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatIconModule, MatRadioModule, MatInputModule, MatDatepickerModule, MatNativeDateModule],
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

  maxPrice: number = 0;
  minPrice: number = 0;
  brands: Brand[] = [];
  used: boolean = true;
  startDate: Date = new Date();
  endDate: Date = new Date();
  minLitre: number = 0;
  maxLitre: number = 0;
  selectedBrand: string = "";
  specification: string = "";
  sortBy: string = "id";
  sortDir: SortDirection = SortDirection.ASC;
  private carSearchCriteria: CarSearchCriteria = {};

  loggedIn: boolean = false;

  constructor(private brandService: BrandService, private searchService: SearchService, private authService: AuthService, private carDataService: CarDataService) { }

  // public searchModel: SearchModel<CarSearchCriteria> = {
  //   searchRequest: {
  //     pageSize: 10,
  //     pageIndex: 0,
  //     sortDir: "ASC",
  //     sortName: "id"
  //   },
  //   model: {
  //     // brand: "",
  //     // specification: "",
  //     // litre: [0,1000],
  //     // used: true,
  //     // price: [0,1000],
  //     // releaseDateTime: [new Date('2024-09-08T12:35:12.920+00:00'),new Date('2026-09-08T12:35:12.920+00:00')]
  //   }
  // }

  search() {
    this.submitVariables();
    this.searchService.searchCars().subscribe((carPage: Page<Car>) => {
      if (carPage === null) {
        console.error("No content was returned from the request")
        carPage = this.carDataService.getCarsPage();
        carPage.content = [];
        carPage.totalElements = 0;
        carPage.totalPages = 0;
      }
      this.carDataService.setCarsPage(carPage);        
    }
    )
  }

  getAllBrands(): Observable<Brand[]> {
    return this.brandService.getAllBrands();
  }


  ngOnInit(): void {
    this.loggedIn = this.authService.isLoggedIn();
    this.getAllBrands().subscribe({
      next: (brands) => {
        this.brands = brands;
      },
      error: (e) => {
        console.error("Error gettings brands", e)
        this.brands = [];
      }
    });
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  submitVariables() {
    this.carSearchCriteria = {
      brand: this.selectedBrand,
      specification: this.specification,
      litre: [this.minLitre, this.maxLitre],
      used: this.used,
      price: [this.minPrice, this.maxPrice],
      releaseDateTime: [this.startDate, this.endDate]
    }
    this.searchService.setSortBy(this.sortBy);
    this.searchService.setSortDir(this.sortDir);

    this.searchService.setCriteria(this.carSearchCriteria);
  }
}