// car-search.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarSearchCriteria } from './car-search-criteria.models';
import { SearchRequest, SortDirection } from './search-request.models';
import { SearchModel } from './search-model.models';
import { Page } from './page.models';
import { Observable } from 'rxjs';
import { CarService } from './car.service';
import { Car } from './car.models';

@Injectable({ providedIn: 'root' })
export class SearchService {
  private baseUrl = "/api/car"

  constructor(private http: HttpClient, private carService: CarService) {}

  private searchModel: SearchModel<CarSearchCriteria> = {
    searchRequest: {
      pageSize: 10,
      pageIndex: 0,
      sortDir: SortDirection.ASC,
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

  searchCars(): Observable<Page<Car>> {
    const savedPage = localStorage.getItem('currentPage');
    this.searchModel.searchRequest.pageIndex = (savedPage ? +savedPage : 1) -1;

    return this.carService.getPageOfCarsWithFilter(this.searchModel);
  }

  setCriteria(carSearchCriteria: CarSearchCriteria) {
    this.searchModel.model = structuredClone(carSearchCriteria);
  }

  getCriteria() {
    return structuredClone(this.searchModel.model);
  }

  setRequest(searchRequest: SearchRequest) {
    this.searchModel.searchRequest = structuredClone(searchRequest);
  }

  getRequest() {
    return structuredClone(this.searchModel.searchRequest);
  }

  setSortBy(sortName: string) {
    return this.searchModel.searchRequest.sortName = sortName;
  }

  getSortBy() {
    return this.searchModel.searchRequest.sortName;
  }

  setSortDir(searchDir: SortDirection) {
    this.searchModel.searchRequest.sortDir = searchDir;
  }

  getSortDir() {
    return this.searchModel.searchRequest.sortDir;
  }
}
