package io.chrismajor.wlt.util;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.ui.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Map DB beans to UI beans / UI beans to DB beans for like types.
 *
 * Given the simplicity of the app at the moment, this class will be sufficient.
 * If the app were to scale out at all, implement a data mapping framework like MapStruct
 */
public class DataMappingUtil {
    public static Product mapNewProduct(ProductEntity productEntity) {
        Product product = new Product();

        if (productEntity != null) {
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setPrice(productEntity.getPrice());
            product.setRef(productEntity.getRef());
        }

        return product;
    }

    public static List<Product> mapNewProductList(List<ProductEntity> entities) {
        List<Product> products = new ArrayList<>();

        for (ProductEntity entity : entities) {
            products.add(DataMappingUtil.mapNewProduct(entity));
        }

        return products;
    }

    public static ProductEntity mapNewProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();

        if (product != null) {
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setRef(product.getRef());
        }

        return productEntity;
    }

    public static void mapProductToProductEntity(Product src, ProductEntity dest) {
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPrice(src.getPrice());
        dest.setRef(src.getRef());
    }

    // TODO: user DB to UI

    // TODO: user UI to DB

    /**
     * Create a random product ref
     *
     * This method will need to be updated if app was to scale,
     * in order to ensure uniqueness without having to harass the database
     *
     * @return a new product ref
     */
    public static String createProductRef() {
        return UUID.randomUUID().toString();
    }
}
