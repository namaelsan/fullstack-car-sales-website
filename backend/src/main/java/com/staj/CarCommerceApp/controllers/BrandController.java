package com.staj.CarCommerceApp.controllers;



import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.services.BrandService;
import com.staj.CarCommerceApp.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final LoggingService loggingService;

    @GetMapping("/find-by-id")
    public ResponseEntity<Brand> getBrandById(@RequestParam("id") String id ) {
        Brand brand = brandService.findBrandById(Long.parseLong(id));
        if (brand != null) {
            return new ResponseEntity<>(brand, HttpStatus.OK);
        }
        loggingService.logInfo("Brand not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        if (!brands.isEmpty()) {
            return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
        }
        loggingService.logInfo("Brands not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        brand = brandService.createBrand(brand);

        if(brand == null){
            loggingService.logError("Brandname already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand, @PathVariable Long id) {
        brand = brandService.updateBrand(brand, id);
        if(brand == null){
            loggingService.logError("Brandname already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        try {
            brandService.removeBrand(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            loggingService.logError("Brand doesnt exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
