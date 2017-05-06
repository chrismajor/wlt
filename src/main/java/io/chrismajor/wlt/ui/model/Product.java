package io.chrismajor.wlt.ui.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Bean to describe a product.
 */
public class Product {

    /** name of the product */
    @NotNull(message = "Please enter a product name")
    @Size(min = 1, message = "Please enter a product name")
    private String name;

    /** description of the product */
    @NotNull(message = "Please enter a product name")
    @Size(min = 1, message = "Please enter a description")
    private String description;

    /** price of the product */
    @NotNull(message = "Please enter a price greater than zero")
    @DecimalMin(value = "0", message = "Please enter a price greater than zero")
    private BigDecimal price;

    /** UI-friendly ID for the product */
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
}
