package io.chrismajor.wlt.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Bean to denote a product entity from the DB.
 */
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

    /** Date & time the record was updated. TODO: should this be a Date / other? */
    private Timestamp updatedDatetime;

    /** private key for the product */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    /** ID of the user that created the product */
    private Long createdUserId;

    /** ID of the user that updated the product */
    private Long updatedUserId;

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
