package com.clevertec.strezhik.service;

import com.clevertec.strezhik.entity.Product;

import java.util.Map;

public interface StoreService {

    void createCheck(String order);

    Map<Product, Integer> putProductsInCart(String[] d);
}
