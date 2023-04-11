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

   /* public Card() {
    }

    public Card(BigDecimal discount, Integer id, String name) {
        this.discount = discount;
        this.id = id;
        this.name = name;
    }*/

  /*  public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CardImpl{" +
                "discount=" + discount +
                ", id=" + id +
                '}';
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

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
