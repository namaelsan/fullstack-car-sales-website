import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user.models';
import { Car } from './car.models';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private baseUrl = "/api/car"
  
    constructor(private http: HttpClient) {  }
    
    createCarSale(car: Car): Observable<Car>{
      return this.http.post<Car>(`${this.baseUrl}/`, car);
    }
  
    updateCarSale(car: Car): Observable<Car>{
      return this.http.patch<Car>(`${this.baseUrl}/${car.id}`, car);
    }

    deleteCarSale(id: number): void{
      this.http.delete(`${this.baseUrl}/${id}`)
    }

    getCarSalesByPage(page: number): Observable<Car[]> {
      return this.http.get<Car[]>(`${this.baseUrl}/?page=${page-1}`) //first page is index 0
    }
}