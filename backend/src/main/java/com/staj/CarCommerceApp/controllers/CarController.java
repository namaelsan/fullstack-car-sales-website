package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.models.Car;
import com.staj.CarCommerceApp.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
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
}
