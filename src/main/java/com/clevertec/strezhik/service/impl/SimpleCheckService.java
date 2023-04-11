package com.clevertec.strezhik.service.impl;

import com.clevertec.strezhik.entity.Card;
import com.clevertec.strezhik.entity.Product;
import com.clevertec.strezhik.service.CheckService;
import com.clevertec.strezhik.service.exception.CardNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class SimpleCheckService implements CheckService {

    private final Map<Product, Integer> shoppingСart = new HashMap<>();

    @Override
    public Map<Product, Integer> getShoppingСart() {
        return shoppingСart;
    }

    @Override
    public BigDecimal generalСost() {
        BigDecimal cost = BigDecimal.valueOf(0);
        for (Product product : getShoppingСart().keySet()) {
            cost = cost.add(nominationCost(product, getShoppingСart().get(product)));
        }
        return cost;
    }

    @Override
    public BigDecimal discountAmount(Card card, BigDecimal cost) {
        if (card != null) {
            return cost.divide(new BigDecimal(100)).multiply(card.getDiscount())
                    .setScale(3, RoundingMode.CEILING);
        } else throw new CardNotFoundException();
    }

    @Override
    public BigDecimal costWithDiscount(Card card, BigDecimal cost) {
        if (card != null) {
            return cost.subtract(discountAmount(card, cost));
        } else throw new CardNotFoundException();
    }

    @Override
    public BigDecimal nominationCost(Product product, Integer value) {
        return product.getPrice().multiply(BigDecimal.valueOf(value));
    }
}
