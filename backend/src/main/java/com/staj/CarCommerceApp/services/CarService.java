package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.models.CarPage;
import com.staj.CarCommerceApp.models.CarSearchCriteria;
import com.staj.CarCommerceApp.models.SearchModel;
import com.staj.CarCommerceApp.repositories.CarCriteriaRepository;
import com.staj.CarCommerceApp.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BrandService brandService;
    private final CarCriteriaRepository carCriteriaRepository;

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

    public Page<Car> getPageOfCarsWithFilter(SearchModel<CarSearchCriteria> searchModel) {
        Page<Car> carPage = carCriteriaRepository.findAllWithFilters(searchModel);

        if (carPage.hasContent()) {
            return carPage;
        }
        return null;
    }
}
