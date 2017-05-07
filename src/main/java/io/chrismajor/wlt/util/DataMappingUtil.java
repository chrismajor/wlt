package io.chrismajor.wlt.util;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.ui.model.Product;

import java.util.UUID;

/**
 * Map DB beans to UI beans / UI beans to DB beans for like types.
 *
 * Given the simplicity of the app at the moment, this class will be sufficient.
 * If the app were to scale out at all, implement a data mapping framework like MapStruct
 */
public class DataMappingUtil {
    public static Product mapProductDbToUi(ProductEntity productEntity) {
        Product product = new Product();
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setPrice(productEntity.getPrice());
        product.setRef(productEntity.getRef());

        return product;
    }

    public static ProductEntity mapProductUiToDb(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setRef(product.getRef());

        return productEntity;
    }

    // TODO: user DB to UI

    // TODO: user UI to DB

    public static String createProductRef() {
        return UUID.randomUUID().toString();
    }
}
