package io.chrismajor.wlt.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Bean to denote a product entity from the DB.
 */
@Entity
@Table(name = "product")
public class ProductEntity {

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

    /** Date & time the record was created */
    private Timestamp createdDatetime;

    /** ID of the user that created the product */
    private Long createdUserId;

    /** Date & time the record was updated */
    private Timestamp updatedDatetime;

    /** ID of the user that updated the product */
    private Long updatedUserId;

    /** Date & time the record was deleted. If null, hasn't been deleted yet! */
    private Timestamp deletedDatetime;

    /** ID of the user that updated the product */
    private Long deletedUserId;

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

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public Long getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(Long updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public Timestamp getDeletedDatetime() {
        return deletedDatetime;
    }

    public void setDeletedDatetime(Timestamp deletedDatetime) {
        this.deletedDatetime = deletedDatetime;
    }

    public Long getDeletedUserId() {
        return deletedUserId;
    }

    public void setDeletedUserId(Long deletedUserId) {
        this.deletedUserId = deletedUserId;
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
                Objects.equals(createdUserId, that.createdUserId) &&
                Objects.equals(updatedDatetime, that.updatedDatetime) &&
                Objects.equals(updatedUserId, that.updatedUserId) &&
                Objects.equals(deletedDatetime, that.deletedDatetime) &&
                Objects.equals(deletedUserId, that.deletedUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, ref, createdDatetime, createdUserId, updatedDatetime, updatedUserId, deletedDatetime, deletedUserId);
    }
}
