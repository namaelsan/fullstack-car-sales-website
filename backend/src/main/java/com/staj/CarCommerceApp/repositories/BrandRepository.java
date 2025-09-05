package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    public Brand findOneByBname(String bname);
}
