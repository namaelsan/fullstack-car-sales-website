package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.models.CarSearchCriteria;
import com.staj.CarCommerceApp.models.SearchModel;
import com.staj.CarCommerceApp.models.SearchRequest;
import com.staj.CarCommerceApp.repositories.BrandRepository;
import com.staj.CarCommerceApp.repositories.CarCriteriaRepository;
import com.staj.CarCommerceApp.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Mock
    private BrandService brandService;

    @Mock
    private CarCriteriaRepository carCriteriaRepository;

    @Test
    void createCarSale_ShouldReturnCreatedCarSale_WhenCreated() {
        Brand brand = Brand.builder().id(1L).bname("TestBrand").build();
        Car car = Car.builder().id(1L)
                .brand(brand)
                .releaseDateTime(new Timestamp(System.currentTimeMillis()))
                .used(true)
                .specification("1")
                .price(BigDecimal.valueOf(100))
                .litre(50F)
                .build();
        when(brandService.findBrandById(1L)).thenReturn(brand);
        when(carRepository.save(Mockito.any(Car.class))).thenReturn(car);

        var newCar = carService.createCarSale(car);

        assertEquals(car, newCar);
    }

    @Test
    void createCarSale_ShouldReturnNull_WhenBrandNotFound() {
        Brand brand = Brand.builder().id(1L).bname("TestBrand").build();
        Car car = Car.builder().id(1L)
                .brand(brand)
                .releaseDateTime(new Timestamp(System.currentTimeMillis()))
                .used(true)
                .specification("1")
                .price(BigDecimal.valueOf(100))
                .litre(50F)
                .build();
        when(brandService.findBrandById(1L)).thenReturn(null);

        var newCar = carService.createCarSale(car);

        assertNull(newCar);
    }

    @Test
    void updateCarSale_ShouldReturnUpdatedCarSale_WhenUpdated() {
        Brand brand = Brand.builder().id(1L).bname("TestBrand").build();
        Car car = Car.builder().id(1L)
                .brand(brand)
                .releaseDateTime(new Timestamp(System.currentTimeMillis()))
                .used(true)
                .specification("1")
                .price(BigDecimal.valueOf(100))
                .litre(50F)
                .build();
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(Mockito.any(Car.class))).thenReturn(car);

        var updatedCar = carService.updateCarSale(car, 1L);

        assertEquals(car, updatedCar);
    }

    @Test
    void updateCarSale_ShouldThrow_WhenIdNotFound() {
        Brand brand = Brand.builder().id(1L).bname("TestBrand").build();
        Car car = Car.builder().id(1L)
                .brand(brand)
                .releaseDateTime(new Timestamp(System.currentTimeMillis()))
                .used(true)
                .specification("1")
                .price(BigDecimal.valueOf(100))
                .litre(50F)
                .build();
        when(carRepository.findById(1L)).thenReturn(null);


        assertThrows(RuntimeException.class, () -> carService.updateCarSale(car, 1L));
    }

    @Test
    void removeCarSale_ShouldNotThrow_WhenDeleted() {
        Brand brand = Brand.builder().id(1L).bname("TestBrand").build();
        Car car = Car.builder().id(1L)
                .brand(brand)
                .releaseDateTime(new Timestamp(System.currentTimeMillis()))
                .used(true)
                .specification("1")
                .price(BigDecimal.valueOf(100))
                .litre(50F)
                .build();
        when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(car));

        assertDoesNotThrow(() -> carService.removeCarSale(1L));
    }

    @Test
    void removeCarSale_ShouldThrow_WhenIdNotFound() {
        when(carRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class,() -> carService.removeCarSale(1L));
    }

    @Test
    void getCarById_ShouldReturnCar_WhenFound() {
        Brand brand = Brand.builder().id(1L).bname("TestBrand").build();
        Car car = Car.builder().id(1L)
                .brand(brand)
                .releaseDateTime(new Timestamp(System.currentTimeMillis()))
                .used(true)
                .specification("1")
                .price(BigDecimal.valueOf(100))
                .litre(50F)
                .build();
        when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(car));

        var result = carService.getCarById(1L);

        assertEquals(car, result);
    }

    @Test
    void getCarById_ShouldReturnNull_WhenNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        var result = carService.getCarById(1L);

        assertNull(result);
    }

    @Test
    void getPageOfCarsWithFilter_ShouldReturnPageWithContent_WhenFound() {
        CarSearchCriteria criteria = new CarSearchCriteria();
        SearchRequest request = new SearchRequest();
        request.setPageIndex(0);
        request.setPageSize(10);
        request.setSortName("price");
        request.setSortDir(Sort.Direction.ASC);

        SearchModel<CarSearchCriteria> searchModel = new SearchModel<>();
        searchModel.setModel(criteria);
        searchModel.setSearchRequest(request);

        List<Car> cars = List.of(new Car(), new Car());
        Page<Car> carPage = new PageImpl<>(cars);

        when(carCriteriaRepository.findAllWithFilters(Mockito.any(SearchModel.class))).thenReturn(carPage);

        Page<Car> result = carService.getPageOfCarsWithFilter(searchModel);

        assertNotNull(result);
        assertEquals(cars, result.getContent());
    }

    @Test
    void getPageOfCarsWithFilter_ShouldReturnNull_WhenNotFound() {
        CarSearchCriteria criteria = new CarSearchCriteria();
        SearchRequest request = new SearchRequest();
        request.setPageIndex(0);
        request.setPageSize(10);
        request.setSortName("price");
        request.setSortDir(Sort.Direction.ASC);

        SearchModel<CarSearchCriteria> searchModel = new SearchModel<>();
        searchModel.setModel(criteria);
        searchModel.setSearchRequest(request);

        Page<Car> carPage = new PageImpl<>(List.of());
        when(carCriteriaRepository.findAllWithFilters(Mockito.any(SearchModel.class))).thenReturn(carPage);


        Page<Car> result = carService.getPageOfCarsWithFilter(searchModel);


        assertNull(result);
    }
}