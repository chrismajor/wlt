package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.repository.ProductRepository;
import io.chrismajor.wlt.ui.model.Product;
import io.chrismajor.wlt.util.DataMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ProductRepository repository;

    public List<Product> getProductList() {
        List<Product> list = new ArrayList<>();

        // mock out the DB bit for now
        Product product1 = new Product();
        product1.setName("Hat");
        product1.setDescription("A pretty swanky top hat");
        product1.setPrice(new BigDecimal(1000));
        product1.setRef("abc123");

        Product product2 = new Product();
        product2.setName("Bulldozer");
        product2.setDescription("It's going to dig up some crazy stuff");
        product2.setPrice(new BigDecimal(50000));
        product2.setRef("def456");

        Product product3 = new Product();
        product3.setName("Swordfish");
        product3.setDescription("Look at it's crazy face");
        product3.setPrice(new BigDecimal(1000));
        product3.setRef("abc123");

        list.add(product1);
        list.add(product2);
        list.add(product3);

        return list;
    }


    public Product getProduct(String ref) {
        // mock out the DB bit for now
        Product product1 = new Product();
        product1.setName("Hat");
        product1.setDescription("A pretty swanky top hat");
        product1.setPrice(new BigDecimal(1000));
        product1.setRef("abc123");
        return product1;
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
