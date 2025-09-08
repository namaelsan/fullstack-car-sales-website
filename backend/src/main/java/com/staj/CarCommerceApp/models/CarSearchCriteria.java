package com.staj.CarCommerceApp.models;

import com.staj.CarCommerceApp.entities.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

@Data
public class CarSearchCriteria {
    private String brand;

    private String specification;

    private ArrayList<Float> litre;

    private Boolean used;

    private ArrayList<BigDecimal> price;

    private ArrayList<Timestamp> releaseDateTime;
}
