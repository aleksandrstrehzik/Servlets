package com.clevertec.strezhik.service;

import com.clevertec.strezhik.entity.Product;

import java.util.List;

public interface ProductService {

    void save(String id, String marking, String type, String is_on_discount,
              String description, String price);

    Product selectById(String id);

    void update(String id, String marking, String type, String is_on_discount,
                String description, String price);

    void delete(String id);

    List<Product> findPortion(String pageNumber, String pageSize);
}
