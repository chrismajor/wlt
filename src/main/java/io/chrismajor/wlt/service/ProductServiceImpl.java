package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.ProductEntity;
import io.chrismajor.wlt.exception.ProductNotFoundException;
import io.chrismajor.wlt.exception.ServiceException;
import io.chrismajor.wlt.repository.ProductRepository;
import io.chrismajor.wlt.ui.model.Product;
import io.chrismajor.wlt.util.DataMappingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
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

    // TODO: @Transactional annotations

    @Autowired
    private ProductRepository repository;

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Return the active list of products
     *
     * @return list of products
     * @throws ServiceException exception thrown if IO issue is found
     * */
    public List<Product> getProductList() throws ServiceException {
        try {
            List<ProductEntity> entities = repository.getProducts();
            return DataMappingUtil.mapNewProductList(entities);
        }
        catch (IOException e) {
            log.error("IOException caught when reading from the database", e);
            throw new ServiceException("Error occurred when accessing database");
        }
    }

    /**
     * Return a single Product, given it's ref
     *
     * @param ref the product ref
     * @return the product
     * @throws ProductNotFoundException exception thrown if product not found
     * @throws ServiceException exception thrown if IO issue is found
     * */
    public Product getProduct(String ref) throws ProductNotFoundException, ServiceException {
        ProductEntity entity = this.getProductEntity(ref);
        return DataMappingUtil.mapNewProduct(entity);
    }

    /**
     * Update a product using the details passed in via the UI.
     * First grab the persisted details, then map the updated details on top. Then persist!
     *
     * @param product the product details to persist
     * @throws ProductNotFoundException exception thrown if product not found
     * @throws ServiceException exception thrown if IO issue is found
     */
    public void updateProduct(Product product) throws ProductNotFoundException, ServiceException {
        if (product == null) {
            log.warn("null product passed to updateProduct service method");
            throw new ProductNotFoundException("null product passed to updateProduct service method");
        }

        // fetch the currently persisted details for this product
        ProductEntity productEntity = this.getProductEntity(product.getRef());

        // map the updated details to this product, & persist
        DataMappingUtil.mapProductToProductEntity(product, productEntity);
        repository.save(productEntity);
    }


    /**
     * Create a new product via the details passed in from the UI.
     * Assign this new product a unique ref.
     *
     * @param product details of the product we want to persist
     * @return boolean to denote success
     * @throws ServiceException exception thrown in case of IO issues
     */
    public boolean createProduct(Product product) throws ServiceException {
        try {
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
        catch (IOException e) {
            log.error("IOException caught when reading from the database", e);
            throw new ServiceException("Error occurred when accessing database");
        }
    }


    /**
     * Set a product as "deleted" in the database. Doesn't actually remove the record from the database,
     * but any read operations should be written to ignore "deleted" records.
     *
     * @param ref product ref
     * @return boolean to note the success of the operation
     * @throws ProductNotFoundException exception thrown if product not found
     * @throws ServiceException exception thrown if IO issue is found
     */
    public boolean deleteProduct(String ref) throws ProductNotFoundException, ServiceException {
        ProductEntity entity = this.getProductEntity(ref);
        entity.setDeletedDatetime(new Timestamp(new Date().getTime()));
        // TODO: deleted user
        repository.save(entity);
        return true;
    }

    /**
     * Private operation for fetching a product entity from the DB by it's ref field
     *
     * Null safe, includes error handling for products that can't be found
     *
     * @param ref the product ref
     * @return the product entity
     * @throws ProductNotFoundException exception thrown if there's data issues, or product can't be found!
     */
    private ProductEntity getProductEntity(String ref) throws ProductNotFoundException, ServiceException {
        if (StringUtils.isEmpty(ref)) {
            throw new ProductNotFoundException("Invalid ref parameter :: " + ref);
        }

        try {
            ProductEntity entity = repository.getProductByRef(ref);

            if (entity == null) {
                log.warn("No product found when searching DB with ref " + ref);
                throw new ProductNotFoundException("Unable to find product using ref " + ref);
            }
            else {
                return entity;
            }
        }
        catch (IOException e) {
            log.error("IOException caught when reading from the database", e);
            throw new ServiceException("Error occurred when accessing database");
        }
    }
}
