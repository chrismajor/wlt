package io.chrismajor.wlt.service;

import io.chrismajor.wlt.exception.ProductNotFoundException;
import io.chrismajor.wlt.ui.model.Product;
import java.util.List;

/**
 * Interface for product service
 */
public interface ProductService {

    /**
     * Get a list of products, ready for the UI
     * @return list of products
     */
    List<Product> getProductList();

    /**
     * Get the details of an individual product by it's ref field
     * @param ref the product ref
     * @return the product object
     */
    Product getProduct(String ref) throws ProductNotFoundException;

    /**
     * Update a product's details
     * @param product the product details to persist
     * @return boolean to denote the success of the operation
     */
    void updateProduct(Product product) throws ProductNotFoundException;

    /**
     * Create a new product
     * @param product details of the product we want to persist
     * @return the persisted product
     */
    boolean createProduct(Product product);

    /**
     * Mark a product as deleted in the database
     * @param product the product to delte
     * @return boolean to note the success of the operation
     */
    boolean deleteProduct(Product product) throws ProductNotFoundException;
}
