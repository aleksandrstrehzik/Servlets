package com.clevertec.strezhik.service;

import com.clevertec.strezhik.entity.Card;
import com.clevertec.strezhik.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface CheckService {
    Map<Product, Integer> getShoppingСart();

    BigDecimal generalСost();

    BigDecimal discountAmount(Card card, BigDecimal cost);

    BigDecimal costWithDiscount(Card card, BigDecimal cost);

    BigDecimal nominationCost(Product product, Integer value);
}
