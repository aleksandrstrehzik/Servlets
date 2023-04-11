package com.clevertec.strezhik.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private boolean IsOnDiscount;
    private String marking;
    private BigDecimal price;
    private Type type;
    private String description;
    private Integer id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return IsOnDiscount == product.IsOnDiscount && Objects.equals(marking, product.marking) && Objects.equals(price, product.price)
                && type == product.type && Objects.equals(description, product.description) && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IsOnDiscount, marking, price, type, description, id);
    }
}
