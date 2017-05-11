package io.chrismajor.wlt.service;

import io.chrismajor.wlt.Application;
import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.exception.ProductNotFoundException;
import io.chrismajor.wlt.exception.ServiceException;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;

/**
 * Unit tests for ProductService
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;


    @Test
    public void testGetProductList() throws Exception {
        ProductEntity testEntity1 = new ProductEntity();
        testEntity1.setName("Spaniel");
        testEntity1.setDescription("mental, but cheerful");
        testEntity1.setPrice(new BigDecimal(500));
        testEntity1.setRef("abc123");
        testEntity1.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity1.setCreatedUserId(1L);

        ProductEntity testEntity2 = new ProductEntity();
        testEntity2.setName("Boat");
        testEntity2.setDescription("quick. and stylish! ;)");
        testEntity2.setPrice(new BigDecimal(4500));
        testEntity2.setRef("def456");
        testEntity2.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity2.setCreatedUserId(1L);

        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(testEntity1);
        productEntities.add(testEntity2);

        given(this.repository.getProducts()).willReturn(productEntities);

        try {
            this.productService.getProductList();
        }
        catch (ServiceException e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetProductList_IOException() throws Exception {
        given(this.repository.getProducts()).willThrow(new IOException());

        try {
            this.productService.getProductList();
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.assertNotNull(e);
        }

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

    @Test
    public void testGetProduct_noProductFound() throws Exception {
        String ref = "abc123";
        given(this.repository.getProductByRef(ref)).willReturn(null);

        try {
            productService.getProduct(ref);

            // if we get this far, fail - should have thrown an exception
            Assert.fail();
        }
        catch (ProductNotFoundException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testGetProduct_IOException() throws Exception {
        String ref = "abc123";
        given(this.repository.getProductByRef(ref)).willThrow(new IOException());


        try {
            productService.getProduct(ref);

            // if we get this far, fail - should have thrown an exception
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.assertNotNull(e);
        }

    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Spaceship");
        product.setDescription("wookie sold separately");
        product.setPrice(new BigDecimal(200));

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");

        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            boolean success = this.productService.createProduct(product);
            Assert.assertTrue(success);
        }
        catch (ServiceException e) {
            Assert.fail();
        }
    }

    public void testCreateProduct_IOException() {
        Product product = new Product();
        product.setName("Spaceship");
        product.setDescription("wookie sold separately");
        product.setPrice(new BigDecimal(200));

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");

        given(this.repository.save(testEntity)).willThrow(new IOException());

        try {
            this.productService.createProduct(product);

            // if we get this far, fail - should have thrown an exception
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setName("Spaceship");
        product.setDescription("wookie sold separately");
        product.setPrice(new BigDecimal(200000));
        product.setRef("123abc");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");


        given(this.repository.getProductByRef("123abc")).willReturn(testEntity);
        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            this.productService.updateProduct(product);
        }
        catch (ProductNotFoundException e) {
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdateProduct_refMismatch() throws Exception {
        Product product = new Product();
        product.setName("Spaceship");
        product.setDescription("wookie sold separately");
        product.setPrice(new BigDecimal(200000));
        product.setRef("123abc");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");


        given(this.repository.getProductByRef("123abc")).willReturn(null);
        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            this.productService.updateProduct(product);

            Assert.fail();
        }
        catch (ProductNotFoundException e) {
            Assert.assertNotNull(e);
        }
        catch (ServiceException e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdateProduct_nullProductParam() {
        try {
            this.productService.updateProduct(null);

            Assert.fail();
        }
        catch (ProductNotFoundException e) {
            Assert.assertNotNull(e);
        }
        catch (ServiceException e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdateProduct_IOException() throws Exception{
        Product product = new Product();
        product.setName("Spaceship");
        product.setDescription("wookie sold separately");
        product.setPrice(new BigDecimal(200000));
        product.setRef("123abc");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");


        given(this.repository.getProductByRef("123abc")).willThrow(new IOException());
        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            this.productService.updateProduct(product);

            Assert.fail();
        }
        catch (ProductNotFoundException e) {
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testDeleteProduct() throws Exception {

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");

        given(this.repository.getProductByRef("123abc")).willReturn(testEntity);
        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            this.productService.deleteProduct("123abc");
        }
        catch (ProductNotFoundException e) {
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.fail();
        }
    }

    @Test
    public void testDeleteProduct_noProductFound() throws Exception {

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");

        given(this.repository.getProductByRef("123abc")).willReturn(null);
        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            this.productService.deleteProduct("123abc");

            Assert.fail();
        }
        catch (ProductNotFoundException e) {
            Assert.assertNotNull(e);
        }
        catch (ServiceException e) {
            Assert.fail();
        }

    }

    @Test
    public void testDeleteProduct_IOException() throws Exception {

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Spaceship");
        testEntity.setDescription("wookie sold separately");
        testEntity.setPrice(new BigDecimal(200));
        testEntity.setCreatedDatetime(new Timestamp(1493593200000L));
        testEntity.setRef("abc123");

        given(this.repository.getProductByRef("123abc")).willThrow(new IOException());
        given(this.repository.save(testEntity)).willReturn(testEntity);

        try {
            this.productService.deleteProduct("123abc");

            Assert.fail();
        }
        catch (ProductNotFoundException e) {
            Assert.fail();
        }
        catch (ServiceException e) {
            Assert.assertNotNull(e);
        }


    }
}
