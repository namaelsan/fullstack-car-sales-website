import { Injectable } from '@angular/core';
import { Brand } from './car.models';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  private baseUrl = "/api/brand"

  constructor(private http: HttpClient) { }

  getAllBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.baseUrl + "/");
  }

  createBrand(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>(this.baseUrl + "/", brand);
  }

  updateBrand(brand: Brand): Observable<Brand> {
    return this.http.patch<Brand>(`${this.baseUrl}/${brand.id}`, brand)
  }

  deleteBrand(id: number): Observable<Object> {
    return this.http.delete(this.baseUrl + "/" + id);
  }
}
