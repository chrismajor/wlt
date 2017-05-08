package io.chrismajor.wlt.util;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.ui.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Unit tests for DataMappingUtil
 */
@RunWith(SpringRunner.class)
public class DataMappingUtilTests {

    @Test
    public void productUiToDbFull() {
        Product originalProduct = new Product();
        originalProduct.setName("Hat");
        originalProduct.setDescription("Fancy hat for fancy gentlemen.");
        originalProduct.setPrice(new BigDecimal(1000));
        originalProduct.setRef("abc123");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Hat");
        testEntity.setDescription("Fancy hat for fancy gentlemen.");
        testEntity.setPrice(new BigDecimal(1000));
        testEntity.setRef("abc123");

        ProductEntity mappedEntity = DataMappingUtil.mapNewProductEntity(originalProduct);
        Assert.assertEquals(mappedEntity, testEntity);
    }

    @Test
    public void productUiToDbMismatch1() {
        Product originalProduct = new Product();
        originalProduct.setName("Monocle");
        originalProduct.setDescription("Like glasses, but one.");
        originalProduct.setPrice(new BigDecimal(100));
        originalProduct.setRef("abc123");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Monocle");
        testEntity.setDescription("Like glasses, but one.");
        testEntity.setPrice(new BigDecimal(100));
        testEntity.setRef("def456");

        ProductEntity mappedEntity = DataMappingUtil.mapNewProductEntity(originalProduct);
        Assert.assertNotEquals(mappedEntity, testEntity);
    }

    @Test
    public void productUiToDbMismatch2() {
        Product originalProduct = new Product();
        originalProduct.setName("Monocle");
        originalProduct.setDescription("Like glasses, but one.");
        originalProduct.setPrice(new BigDecimal(100));
        originalProduct.setRef("abc123");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Monocle");
        testEntity.setDescription("Like glasses, but one.");
        testEntity.setPrice(new BigDecimal(1000000000));
        testEntity.setRef("abc123");

        ProductEntity mappedEntity = DataMappingUtil.mapNewProductEntity(originalProduct);
        Assert.assertNotEquals(mappedEntity, testEntity);
    }

    @Test
    public void productUiToDbMismatch3() {
        Product originalProduct = new Product();
        originalProduct.setName("Monocle");
        originalProduct.setDescription("Like glasses, but one.");
        originalProduct.setPrice(new BigDecimal(100));
        originalProduct.setRef("abc123");

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Monocle");
        testEntity.setDescription("Glasses for cyclopses.");
        testEntity.setPrice(new BigDecimal(100));
        testEntity.setRef("abc123");

        ProductEntity mappedEntity = DataMappingUtil.mapNewProductEntity(originalProduct);
        Assert.assertNotEquals(mappedEntity, testEntity);
    }

    @Test
    public void productUiToDbPartial() {
        Product originalProduct = new Product();
        originalProduct.setName("Helicopter");
        originalProduct.setPrice(new BigDecimal(2000));

        ProductEntity testEntity = new ProductEntity();
        testEntity.setName("Helicopter");
        testEntity.setPrice(new BigDecimal(2000));

        ProductEntity mappedEntity = DataMappingUtil.mapNewProductEntity(originalProduct);
        Assert.assertEquals(mappedEntity, testEntity);
    }

    @Test
    public void productUiToDbNull() {
        Product originalProduct = new Product();
        ProductEntity testEntity = new ProductEntity();
        ProductEntity mappedEntity = DataMappingUtil.mapNewProductEntity(originalProduct);
        Assert.assertEquals(testEntity, mappedEntity);
    }

    @Test
    public void productDbToUiFull() {
        ProductEntity originalEntity = new ProductEntity();
        originalEntity.setName("Pigmy rhino");
        originalEntity.setDescription("Perfect for apartment living.");
        originalEntity.setPrice(new BigDecimal(300));
        originalEntity.setRef("hhh777");

        Product testProduct = new Product();
        testProduct.setName("Pigmy rhino");
        testProduct.setDescription("Perfect for apartment living.");
        testProduct.setPrice(new BigDecimal(300));
        testProduct.setRef("hhh777");

        Product mappedProduct = DataMappingUtil.mapNewProduct(originalEntity);
        Assert.assertEquals(mappedProduct, testProduct);
    }


    @Test
    public void productDbToUiMismatch1() {
        ProductEntity originalEntity = new ProductEntity();
        originalEntity.setName("Grand piano");
        originalEntity.setDescription("The grandest of pianos");
        originalEntity.setPrice(new BigDecimal(500));
        originalEntity.setRef("ppp999");

        Product testProduct = new Product();
        testProduct.setName("Grand piano");
        testProduct.setDescription("The grandest of pianos");
        testProduct.setPrice(new BigDecimal(500000));
        testProduct.setRef("ppp999");

        Product mappedProduct = DataMappingUtil.mapNewProduct(originalEntity);
        Assert.assertNotEquals(mappedProduct, testProduct);
    }

    @Test
    public void productDbToUiMismatch2() {
        ProductEntity originalEntity = new ProductEntity();
        originalEntity.setName("Grand piano");
        originalEntity.setDescription("The grandest of pianos");
        originalEntity.setPrice(new BigDecimal(500));
        originalEntity.setRef("ppp999");

        Product testProduct = new Product();
        testProduct.setName("Grand piano");
        testProduct.setDescription("Cumbersome, but sounds fantastic");
        testProduct.setPrice(new BigDecimal(500));
        testProduct.setRef("ppp999");

        Product mappedProduct = DataMappingUtil.mapNewProduct(originalEntity);
        Assert.assertNotEquals(mappedProduct, testProduct);
    }

    @Test
    public void productDbToUiMismatch3() {
        ProductEntity originalEntity = new ProductEntity();
        originalEntity.setName("Grand piano");
        originalEntity.setDescription("The grandest of pianos");
        originalEntity.setPrice(new BigDecimal(500));
        originalEntity.setRef("ppp999");

        Product testProduct = new Product();
        testProduct.setName("Grand piano");
        testProduct.setDescription("The grandest of pianos");
        testProduct.setPrice(new BigDecimal(500));
        testProduct.setRef("ppp999111");

        Product mappedProduct = DataMappingUtil.mapNewProduct(originalEntity);
        Assert.assertNotEquals(mappedProduct, testProduct);
    }

    @Test
    public void productDbToUiPartial() {
        ProductEntity originalEntity = new ProductEntity();
        originalEntity.setName("Batmobile");
        originalEntity.setRef("jjj666");

        Product testProduct = new Product();
        testProduct.setName("Batmobile");
        testProduct.setRef("jjj666");

        Product mappedProduct = DataMappingUtil.mapNewProduct(originalEntity);
        Assert.assertEquals(mappedProduct, testProduct);    }

    @Test
    public void productDbToUiNull() {
        ProductEntity originalEntity = new ProductEntity();
        Product testProduct = new Product();
        Product mappedProduct = DataMappingUtil.mapNewProduct(originalEntity);
        Assert.assertEquals(mappedProduct, testProduct);    }
}
