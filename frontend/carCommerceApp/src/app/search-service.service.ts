// car-search.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarSearchCriteria } from './car-search-criteria.models';
import { SearchRequest } from './search-request.models';
import { SearchModel } from './search-model.models';
import { Page } from './page.models';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class SearchService {
  private baseUrl = "/api/car"

  constructor(private http: HttpClient) {}

  private defaultModel: SearchModel<CarSearchCriteria> = {
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

  searchCars(searchModel?: SearchModel<CarSearchCriteria>): Observable<Page> {

    let request;
    if (!searchModel){
      const savedPage = localStorage.getItem('currentPage');
      this.defaultModel.searchRequest.pageIndex = (savedPage ? +savedPage : 1) -1;
      request = this.defaultModel;
    } else {
      request = searchModel;
    }

    return this.http.put<Page>(`${this.baseUrl}/`, request);
  }
}
