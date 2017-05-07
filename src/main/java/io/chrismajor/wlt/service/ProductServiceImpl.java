package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.repository.ProductRepository;
import io.chrismajor.wlt.ui.model.Product;
import io.chrismajor.wlt.util.DataMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        return DataMappingUtil.mapProductListUiToDb(entities);
    }

    public Product getProduct(String ref) {
        ProductEntity entity = repository.getProductByRef(ref);
        return DataMappingUtil.mapProductDbToUi(entity);
    }


    public boolean updateProduct(Product product) {
        return false;
    }


    public boolean createProduct(Product product) {
        ProductEntity entity = DataMappingUtil.mapProductUiToDb(product);
        entity.setCreatedDatetime(new Timestamp(new Date().getTime()));
        repository.save(entity);
        return true;
    }


    public boolean deleteProduct(Product product) {
        return false;
    }
}
