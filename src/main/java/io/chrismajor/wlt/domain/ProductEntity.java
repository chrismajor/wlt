package io.chrismajor.wlt.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Bean to denote a product entity from the DB.
 * TODO: annotate class, vars and methods
 */
public class ProductEntity {

    /** private key for the product */
    private Long id;

    /** name of the product */
    private String name;

    /** description of the product */
    private String description;

    /** price of the product */
    private BigDecimal price;

    /** UI-friendly ID for the product */
    private String ref;

    /** Date & time the record was created. TODO: should this be a Date / other? */
    private Timestamp createdDatetime;

    /** Date & time the record was deleted. If null, hasn't been deleted yet! TODO: should this be a Date / other? */
    private Timestamp deletedTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getDeletedTimestamp() {
        return deletedTimestamp;
    }

    public void setDeletedTimestamp(Timestamp deletedTimestamp) {
        this.deletedTimestamp = deletedTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(ref, that.ref) &&
                Objects.equals(createdDatetime, that.createdDatetime) &&
                Objects.equals(deletedTimestamp, that.deletedTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, ref, createdDatetime, deletedTimestamp);
    }
}
