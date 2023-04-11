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

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarking() {
        return marking;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product() {
    }

    public boolean IsOnDiscount() {
        return IsOnDiscount;
    }

    public void setOnDiscount(boolean onDiscount) {
        IsOnDiscount = onDiscount;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        return "ProductImpl{" +
                "IsOnDiscount=" + IsOnDiscount +
                ", marking='" + marking + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
*/
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
