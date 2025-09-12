package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.models.CarSearchCriteria;
import com.staj.CarCommerceApp.models.SearchModel;
import com.staj.CarCommerceApp.models.SearchRequest;
import com.staj.CarCommerceApp.repositories.BrandRepository;
import com.staj.CarCommerceApp.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final CarService carService;


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

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
