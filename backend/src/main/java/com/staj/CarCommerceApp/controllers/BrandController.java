package com.staj.CarCommerceApp.controllers;


import com.staj.CarCommerceApp.models.Brand;
import com.staj.CarCommerceApp.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        brand = brandService.createBrand(brand);

        if(brand != null){
            return new ResponseEntity<>(brand, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

}
