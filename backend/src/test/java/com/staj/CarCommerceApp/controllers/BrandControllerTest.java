package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.entities.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BrandControllerTest {

    @Autowired
    private BrandController brandController;

    @Test
    void getBrandById_ShouldReturnBrand_WhenFound() {
//        //arrange
//        Brand brand = Brand.builder()
//                .id(1L)
//                .bname("Test").build();
//
//        //act
//        var newBrand = brandController.getBrandById(String.valueOf(1L));
//
//        //assert
//        assertEquals(newBrand.getBody().toString(), brand.toString());
    }

    @Test
    void getAllBrands() {
    }

    @Test
    void createBrand() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void deleteBrand() {
    }
}