package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.models.Car;
import com.staj.CarCommerceApp.repositories.CarRepository;
import com.staj.CarCommerceApp.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;
    public CarController(CarService carService) {this.carService = carService;}

    @GetMapping("")
    public String mainPage() {
        return "Welcome to car website";
    }

    @PostMapping
    public ResponseEntity<Car> createCarSale(@RequestBody Car car) {
        car.setId(null);
        car = carService.createCarSale(car);
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
}
