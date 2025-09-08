package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.models.CarSearchCriteria;
import com.staj.CarCommerceApp.models.SearchModel;
import com.staj.CarCommerceApp.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;
    public CarController(CarService carService) {this.carService = carService;}

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@RequestParam Long id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Car> createCarSale(@RequestBody Car car) {
        car.setId(null);
        car = carService.createCarSale(car);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> updateCarSale(@RequestBody Car car, @PathVariable Long id) {
        car = carService.updateCarSale(car, id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarSale(@PathVariable Long id) {
        carService.removeCarSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Page<Car>> getPageOfCarsWithFilter(@RequestBody SearchModel<CarSearchCriteria> carRequest) {
        Page<Car> cars = carService.getPageOfCarsWithFilter(carRequest);
        if (cars == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println(cars);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
