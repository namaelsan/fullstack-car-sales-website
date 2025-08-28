package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.Brand;
import com.staj.CarCommerceApp.models.Car;
import com.staj.CarCommerceApp.repositories.BrandRepository;
import com.staj.CarCommerceApp.repositories.CarRepository;
import org.springframework.stereotype.Service;


@Service
public class CarService {
    private CarRepository carRepository;
    private BrandService brandService;

    public CarService(CarRepository carRepository, BrandService brandService) {this.carRepository = carRepository; this.brandService = brandService;}

    public Car createCarSale(Car car) {
        Long brandId = car.getBrand().getId();
        if (brandService.findBrand(brandId) == null) {
            return null;
        }
        Brand brand = car.getBrand();
        brand.setName(brandService.findBrand(brand.getId()).getName());
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
