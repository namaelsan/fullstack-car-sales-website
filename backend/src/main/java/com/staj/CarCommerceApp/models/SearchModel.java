package com.staj.CarCommerceApp.models;

import lombok.Data;

@Data
public class SearchModel<T> {
    SearchRequest searchRequest = null;
    T model = null;
}
