package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.util.List;

/**
 * CRUD repository for product persistence
 */
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    /**
     * Retrieve a list of products that haven't been deleted
     * @return list of products
     * @throws IOException
     */
    @Query(value = "from ProductEntity where deletedDatetime is null")
    List<ProductEntity> getProducts() throws IOException;

    /**
     * Get a single product by it's ref field, as long as it hasn't been deleted
     * @param ref the product ref
     * @return the product
     * @throws IOException
     */
    @Query(value = "from ProductEntity where ref = ?1 and deletedDatetime is null")
    ProductEntity getProductByRef(String ref) throws IOException;
}
