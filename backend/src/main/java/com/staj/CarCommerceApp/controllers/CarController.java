package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.models.CarSearchCriteria;
import com.staj.CarCommerceApp.models.SearchModel;
import com.staj.CarCommerceApp.services.CarService;
import com.staj.CarCommerceApp.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final LoggingService loggingService;

    @GetMapping("/")
    public ResponseEntity<Car> getCarById(@RequestParam Long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            loggingService.logInfo("Car not found with id " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Car> createCarSale(@RequestBody Car car) {
        car.setId(null);
        car = carService.createCarSale(car);
        if (car == null) {
            loggingService.logError("Brand not found");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> updateCarSale(@RequestBody Car car, @PathVariable Long id) {
        try {
            car = carService.updateCarSale(car, id);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception e) {
            loggingService.logError("Car not found");
            return new ResponseEntity<>(car, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarSale(@PathVariable Long id) {
        try {
            carService.removeCarSale(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            loggingService.logError("Car couldnt be deleted");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Page<Car>> getPageOfCarsWithFilter(@RequestBody SearchModel<CarSearchCriteria> carRequest) {
        Page<Car> cars = carService.getPageOfCarsWithFilter(carRequest);
        if (cars == null) {
            loggingService.logInfo("Page is empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
