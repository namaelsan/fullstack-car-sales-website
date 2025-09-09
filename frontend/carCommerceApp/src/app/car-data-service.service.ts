import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Car } from './car.models';
import { Page } from './page.models';

@Injectable({
  providedIn: 'root'
})
export class CarDataService {

  constructor() { }

  private page: Page<Car> = {
    empty: false,
    content: [],
    totalElements: 0,
    totalPages: 0,
  }

  private pageSubject = new BehaviorSubject<Page<Car>>(this.page);
  carsPage$ = this.pageSubject.asObservable();

  setCarsPage(carsPage: Page<Car>): void {
    if (carsPage === null){
      console.error("CarsPage couldnt be set");
    }
    this.pageSubject.next(carsPage);
  }

  getCarsPage(): Page<Car> {
    return this.pageSubject.getValue();
  }

  getCarById(id: number): Car | undefined {
    return this.getCarsPage().content.find((c) => c.id === id)
  }
}
