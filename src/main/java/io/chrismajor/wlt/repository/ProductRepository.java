package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Christo on 06/05/2017.
 */
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    // TODO: implement these
    @Query(value = "from ProductEntity where deletedDatetime is null")
    List<ProductEntity> getProducts();

//    ProductEntity getProductByRef(String ref);

    // TODO: delete these?
//    boolean updateProduct(ProductEntity product);

//    ProductEntity createProduct(ProductEntity product);

//    boolean deleteProduct(ProductEntity product);
}
