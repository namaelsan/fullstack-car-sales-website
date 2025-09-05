package com.staj.CarCommerceApp.models;

import org.springframework.data.domain.Sort;

public class CarPage {
    private int pageNumber = 0; // first page is index 0
    private int pageSize = 10;
    private Sort.Direction direction = Sort.Direction.ASC;
    private String sortBy = "id";
}
