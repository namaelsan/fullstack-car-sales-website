package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.Brand;
import com.staj.CarCommerceApp.repositories.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Console;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {this.brandRepository = brandRepository;}

    public Brand findBrand(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand createBrand(@RequestBody Brand brand) {
        brand = brandRepository.save(brand);
        return brand;
    }
}
