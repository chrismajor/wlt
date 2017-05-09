package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.util.List;

/**
 * Created by Christo on 06/05/2017.
 */
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    @Query(value = "from ProductEntity where deletedDatetime is null")
    List<ProductEntity> getProducts() throws IOException;

    @Query(value = "from ProductEntity where ref = ?1 and deletedDatetime is null")
    ProductEntity getProductByRef(String ref) throws IOException;
}
