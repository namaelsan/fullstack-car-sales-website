package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.Car;
import com.staj.CarCommerceApp.repositories.CarRepository;
import org.springframework.stereotype.Service;


@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {this.carRepository = carRepository;}

    public Car createCarSale(Car car) {
        return carRepository.save(car);
    }

    public Car updateCarSale(Car car, Long id) {
        carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car couldnt be found."));
        return carRepository.save(car);
    }

    public void removeCarSale(Long id) {
        carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car couldnt be found."));
        carRepository.deleteById(id);
    }
}
