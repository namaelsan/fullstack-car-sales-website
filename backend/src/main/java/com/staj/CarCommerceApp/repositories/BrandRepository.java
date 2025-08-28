package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
