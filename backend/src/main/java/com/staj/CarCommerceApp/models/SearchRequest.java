package com.staj.CarCommerceApp.models;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class SearchRequest {
    int pageSize = 10;
    int pageIndex = 0;
    Sort.Direction sortDir = Sort.Direction.ASC;
    String sortName;
}

