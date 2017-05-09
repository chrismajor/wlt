package io.chrismajor.wlt.service;

import io.chrismajor.wlt.Application;
import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.repository.ProductRepository;
import io.chrismajor.wlt.ui.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.BDDMockito.given;

/**
 * Created by Christo on 09/05/2017.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @MockBean
    ProductRepository repository;


    public void testGetProductList() {

    }

    public void testGetProductList_IOException() {

    }

    @Test
    public void testGetProduct() throws Exception {
        // time 1493593200000
        // date 2017-05-01 00:00:00

        String ref = "abc123";

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaniel");
        testEntity.setDescription("mental, but cheerful");
        testEntity.setPrice(new BigDecimal(500));
        testEntity.setRef(ref);
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setCreatedUserId(1L);


        given(this.repository.getProductByRef(ref)).willReturn(testEntity);

        Product product = productService.getProduct(ref);
        Assert.assertEquals(product.getName(), testEntity.getName());
        Assert.assertEquals(product.getDescription(), testEntity.getDescription());
        Assert.assertEquals(product.getPrice(), testEntity.getPrice());
        Assert.assertEquals(product.getRef(), testEntity.getRef());
    }

    public void testGetProduct_noProductFound() {

    }

    public void testGetProduct_IOException() {

    }

    public void testCreateProduct() {

    }

    public void testCreateProduct_IOException() {

    }

    public void testUpdateProduct() {

    }
    public void testUpdateProduct_nullProductParam() {

    }
    public void testUpdateProduct_noProductFound() {

    }

    public void testUpdateProduct_IOException() {

    }

    public void testDeleteProduct() {

    }

    public void testDeleteProduct_noProductFound() {

    }

    public void testDeleteProduct_IOException() {

    }


}
