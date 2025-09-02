package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.Brand;
import com.staj.CarCommerceApp.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;


    public Brand findBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand findBrandByName(String bname) {
        return brandRepository.findOneByBname(bname);
    }

    public Brand createBrand(@RequestBody Brand brand) {
        if (findBrandByName(brand.getBname()) != null) {
            return null;
        }

        brand = brandRepository.save(brand);
        return brand;
    }

    public Brand updateBrand(Brand brand, Long id) {
        brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand couldnt be found."));
        brand.setId(id);
        return brandRepository.save(brand);
    }

    public void removeBrand(Long id) {
        brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand couldnt be found."));
        brandRepository.deleteById(id);
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
