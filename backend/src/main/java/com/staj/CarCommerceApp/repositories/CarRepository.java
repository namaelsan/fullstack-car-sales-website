package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
