package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.exception.ProductNotFoundException;
import io.chrismajor.wlt.repository.ProductRepository;
import io.chrismajor.wlt.ui.model.Product;
import io.chrismajor.wlt.util.DataMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Service layer for persistence operations
 *
 * Performs any business logic required, maps between the DB layer and the UI layer
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    public List<Product> getProductList() {
        List<ProductEntity> entities = repository.getProducts();
        return DataMappingUtil.mapNewProductList(entities);
    }

    public Product getProduct(String ref) throws ProductNotFoundException {
        if (StringUtils.isEmpty(ref)) {
            throw new ProductNotFoundException();
        }

        ProductEntity entity = this.getProductEntity(ref);

        if (entity == null) {
            throw new ProductNotFoundException();
        }
        else {
            return DataMappingUtil.mapNewProduct(entity);
        }
    }


    public void updateProduct(Product product) throws ProductNotFoundException {
        if (product == null || StringUtils.isEmpty(product.getRef())) {
            throw new ProductNotFoundException();
        }

        ProductEntity productEntity = this.getProductEntity(product.getRef());

        if (productEntity == null) {
            throw new ProductNotFoundException();
        }

        DataMappingUtil.mapProductToProductEntity(product, productEntity);
        repository.save(productEntity);
    }


    public boolean createProduct(Product product) {
        // create a new ref for the product
        String newRef;
        boolean unique;

        // create a new ref for the product, ensure it's unique in the database
        do {
            newRef = DataMappingUtil.createProductRef();
            ProductEntity entity = repository.getProductByRef(newRef);

            // if we find an entity with this ref, we need to loop round and get a new ref
            unique = entity == null;
        }
        while (!unique);

        ProductEntity entity = DataMappingUtil.mapNewProductEntity(product);
        entity.setRef(newRef);
        entity.setCreatedDatetime(new Timestamp(new Date().getTime()));
        // TODO: created user

        repository.save(entity);
        return true;
    }


    public boolean deleteProduct(Product product) {
        return false;
    }

    private ProductEntity getProductEntity(String ref) {
        return repository.getProductByRef(ref);
    }
}
