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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void findBrandById_ShouldReturnBrand_WhenFound() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findById(1L)).thenReturn(Optional.of(Brand.builder().id(1L)
                .bname("Test")
                .build()));

        var newBrand = brandService.findBrandById(1L);

        assertEquals(newBrand.getBname(), brand.getBname());
    }

    @Test
    void findBrandById_ShouldReturnNull_WhenNotFound() {


        var newBrand = brandService.findBrandById(1L);

        assertNull(newBrand);
    }

    @Test
    void findBrandByName_ShouldReturnBrand_WhenFound() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findOneByBname("Test")).thenReturn(brand);

        var newBrand = brandService.findBrandByName("Test");

        assertEquals(newBrand.getBname(), brand.getBname());
    }

    @Test
    void findBrandByName_ShouldReturnNull_WhenNotFound() {


        var newBrand = brandService.findBrandByName("Test");

        assertNull(newBrand);
    }


    @Test
    void updateBrand_ShouldReturnUpdatedBrand_WhenUpdated() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findById(1L)).thenReturn(Optional.of(Brand.builder().id(1L)
                .bname("Test")
                .build()));
        Brand newBrand = Brand.builder().bname("NewName").id(1L).build();
        when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(newBrand);

        var updatedBrand = brandService.updateBrand(newBrand, 1L);

        assertEquals(updatedBrand.getBname(), newBrand.getBname());
        assertEquals(1L, updatedBrand.getId());
    }

    @Test
    void updateBrand_ShouldThrow_WhenIdNotFound() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findById(1L)).thenReturn(null);


        assertThrows(RuntimeException.class, () -> brandService.updateBrand(brand, 1L));
    }

    @Test
    void removeBrand_ShouldNotThrow_WhenDeleted() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findById(1L)).thenReturn(Optional.of(Brand.builder().id(1L)
                .bname("Test")
                .build()));

        assertDoesNotThrow(() -> brandService.removeBrand(1L));
    }

    @Test
    void removeBrand_ShouldThrow_WhenIdNotFound() {
        Brand brand = new Brand();
        brand.setBname("Test");
        when(brandRepository.findById(1L)).thenReturn(null);


        assertThrows(RuntimeException.class, () -> brandService.removeBrand(1L));
    }

    @Test
    void getAllBrands_ShouldReturnBrandList_WhenFound() {
        Brand brand1 = Brand.builder().bname("Test").id(1L).build();
        Brand brand2 = Brand.builder().bname("Test2").id(2L).build();
        List<Brand> brands = Arrays.asList(brand1, brand2);
        when(brandRepository.findAll()).thenReturn(brands);

        var dbBrands = brandService.getAllBrands();

        assertEquals(dbBrands, brands);
    }

    @Test
    void getAllBrands_ShouldReturnEmptyList_WhenDBEmpty() {
        List<Brand> brands = List.of();
        when(brandRepository.findAll()).thenReturn(brands);

        var dbBrands = brandService.getAllBrands();

        assertEquals(List.of(), dbBrands);
    }
}