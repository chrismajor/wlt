package io.chrismajor.wlt.util;

import io.chrismajor.wlt.domain.Address;
import io.chrismajor.wlt.domain.Person;
import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.ui.model.Product;
import io.chrismajor.wlt.ui.model.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Map DB beans to UI beans / UI beans to DB beans for like types.
 *
 * Given the simplicity of the app at the moment, this class will be sufficient.
 * If the app were to scale out at all, implement a data mapping framework like MapStruct
 *
 * TODO: throw new exception if null input?
 */
public class DataMappingUtil {

    /**
     * Create a new instance of a Product, using the details from a ProductEntity
     * @param productEntity the product entity details
     * @return a fully populated Product
     */
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

    /**
     * Create a new list of Products, using a list of ProductEntitys as input
     * @param entities the list of product entities
     * @return a list of Products
     */
    public static List<Product> mapNewProductList(List<ProductEntity> entities) {
        List<Product> products = new ArrayList<>();

        for (ProductEntity entity : entities) {
            products.add(DataMappingUtil.mapNewProduct(entity));
        }

        return products;
    }

    /**
     * Create a new instance of a ProductEntity, using a Product to populate the detail
     * @param product the Product to use as input
     * @return a new ProductEntity
     */
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

    /**
     * Map a Product's details onto an existing ProductEntity
     * @param src the Product to read the details from
     * @param dest the ProductEntity to write the details to
     */
    public static void mapProductToProductEntity(Product src, ProductEntity dest) {
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPrice(src.getPrice());
        dest.setRef(src.getRef());
    }

    /**
     * Create a new instance of a User entity (and it's nested Person & Address entities) using the details
     * of a UserDetails object
     * @param userDetails the UserDetails object to use as input
     * @return a new User entity
     */
    public static User mapNewUser(UserDetails userDetails) {
        User user = new User();

        if (userDetails != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());

            Address address = new Address();
            address.setAddressLine1(userDetails.getAddressLine1());
            address.setAddressLine2(userDetails.getAddressLine2());
            address.setTown(userDetails.getTown());
            address.setCounty(userDetails.getCounty());
            address.setCountry(userDetails.getCountry());
            address.setPostCode(userDetails.getPostcode());

            Person person = new Person();
            person.setForename(userDetails.getForename());
            person.setSurname(userDetails.getSurname());
            person.setDob(new Timestamp(userDetails.getDob().getTime()));
            person.setAddress(address);
            user.setPerson(person);
        }

        return user;
    }

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
