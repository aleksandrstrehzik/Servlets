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
public class Card {
    private BigDecimal discount;
    private Integer id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(discount, card.discount) &&
                Objects.equals(id, card.id) && Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, id, name);
    }
}
