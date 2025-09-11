package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void findOneByBname_ShouldReturnBrand_WhenFound() {
        Brand brand = new Brand();
        brand.setBname("Test");
        brandRepository.save(brand);

        Brand newBrand = brandRepository.findOneByBname("Test");

        assertEquals(newBrand.toString(), brand.toString());
    }

    @Test
    void findOneByBname_ShouldReturnNull_WhenNotFound() {
        Brand newBrand = brandRepository.findOneByBname("Test");

        assertNull(newBrand);
    }
}