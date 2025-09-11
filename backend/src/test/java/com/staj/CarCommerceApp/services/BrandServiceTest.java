package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.repositories.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @Test
    void createBrand_ShouldReturnCreatedBrand() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(brand);

        var newBrand = brandService.createBrand(brand);

        assertEquals(newBrand.toString(), brand.toString());
    }

    @Test
    void createBrand_ShouldReturnNull_WhenBnameAlreadyExists() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findOneByBname("Test")).thenReturn(brand);


        var newBrand = brandService.createBrand(brand);

        assertNull(newBrand);
    }

    @Test
    void findBrandById_ShouldReturnBran_WhenFound() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(brand);


        var newBrand = brandService.createBrand(brand);

        assertNull(newBrand);
    }

    @Test
    void findBrandByName() {
    }


    @Test
    void updateBrand() {
    }

    @Test
    void removeBrand() {
    }

    @Test
    void getBrandById() {
    }

    @Test
    void getAllBrands() {
    }
}