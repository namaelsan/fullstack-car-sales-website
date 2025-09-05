package com.staj.CarCommerceApp.models;

import com.staj.CarCommerceApp.entities.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CarSearchCriteria {
    private Brand brand;

    private String specification;

    private Float litre;

    private boolean used;

    private BigDecimal price;

    private Timestamp releaseDateTime;
}
