import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SearchModel } from '../search-model.models';
import { CarSearchCriteria } from '../car-search-criteria.models';
import { Brand } from '../car.models';
import { BrandService } from '../brand.service';
import { map, Observable } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule, MatDateRangeInput } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SearchService } from '../search-service.service';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatIconModule, MatRadioModule, MatInputModule, MatDatepickerModule, MatNativeDateModule],
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit{

  maxPrice: number = 0;
  minPrice: number = 0;
  brands: Brand[] = [];
  used: boolean = true;
  startDate: Date = new Date(); 
  endDate: Date = new Date();
  minLitre: number = 0;
  maxLitre: number = 0;

  constructor (private brandService: BrandService, private searchService: SearchService) {}

  public searchModel: SearchModel<CarSearchCriteria> = {
    searchRequest: {
      pageSize: 10,
      pageIndex: 0,
      sortDir: "ASC",
      sortName: "id"
    },
    model: {
      // brand: "",
      // specification: "",
      // litre: [0,1000],
      // used: true,
      // price: [0,1000],
      // releaseDateTime: [new Date('2024-09-08T12:35:12.920+00:00'),new Date('2026-09-08T12:35:12.920+00:00')]
    }
  }

  search() {
    this.searchService.searchCars(this.searchModel)
  }

  getAllBrands(): Observable<Brand[]> {
  
    return this.brandService.getAllBrands();
  }


  ngOnInit(): void {
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
}
