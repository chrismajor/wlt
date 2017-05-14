package io.chrismajor.wlt.ui.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bean to describe a product.
 */
public class Product {

    /** name of the product */
    @NotNull(message = "Please enter a product name")
    @Size(min = 1, max = 255, message = "Please enter a product name")
    private String name;

    /** description of the product */
    @NotNull(message = "Please enter a product name")
    @Size(min = 1, max = 255, message = "Please enter a description")
    private String description;

    /** price of the product */
    @NotNull(message = "Please enter a price greater than zero")
    @DecimalMin(value = "0", message = "Please enter a price greater than zero")
    private BigDecimal price;

    /** UI-friendly ID for the product */
    @NotNull
    @Size(min = 1, max = 255)
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(ref, product.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, ref);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ref='" + ref + '\'' +
                '}';
    }
}
