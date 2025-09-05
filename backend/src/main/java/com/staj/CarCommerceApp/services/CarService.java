package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {
    private CarRepository carRepository;
    private BrandService brandService;

    public CarService(CarRepository carRepository, BrandService brandService) {this.carRepository = carRepository; this.brandService = brandService;}

    public Car createCarSale(Car car) {
        Long brandId = car.getBrand().getId();
        if (brandService.findBrandById(brandId) == null) {
            return null;
        }
        Brand brand = car.getBrand();
        brand.setBname(brandService.findBrandById(brand.getId()).getBname());
        return carRepository.save(car);
    }

    public Car updateCarSale(Car car, Long id) {
        carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car couldnt be found."));
        car.setId(id);
        return carRepository.save(car);
    }

    public void removeCarSale(Long id) {
        carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car couldnt be found."));
        carRepository.deleteById(id);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Page<Car> getPageOfCars(int page) {
        int pageSize = 10;
        Page<Car> carPage = carRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id").ascending()));

        if (carPage.hasContent()) {
            return carPage;
        }
        return null;
    }
}
