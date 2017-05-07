package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.service.ProductService;
import io.chrismajor.wlt.ui.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for product list operations
 *
 * TODO: error handling
 * TODO: XSS validation
 */
@Controller
public class ListController {

    @Autowired
    private ProductService service;


    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * List out all of the active products
     * @param model the model
     * @return the product list view
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.info("here we goooooooooooooooooo");

        // get all of the active products
        List<Product> products = service.getProductList();
        model.addAttribute("products", products);

        // display the product list
        return "list";
    }

    /**
     * View an individual product
     * @param ref the product ref
     * @param model the model
     * @return the product view
     */
    @RequestMapping(value = "/list/product", method = RequestMethod.GET)
    public String product(
            @RequestParam(value="ref", required=true) String ref,
            Model model) {

        // TODO: validate XSS for ref

        // get product by ref, add to the model
        Product product = service.getProduct(ref);
        model.addAttribute("product", product);

        // view the product details
        return "product";
    }

    /**
     * Update the details of a product
     * @param product the details of the updated product
     * @return the 'list' view
     */
    @RequestMapping(value = "/list/product/update", method = RequestMethod.POST)
    public String productUpdate(@Valid Product product, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "newproduct";
        }

        // hand product off to service to be updated
        boolean success = service.updateProduct(product);

        // if the update has been successful, return to the product list
        return "list";
    }

    /**
     * Display the page for creating new products
     * @param model the model
     * @return the 'new product' view
     */
    @RequestMapping(value = "/list/product/new", method = RequestMethod.GET)
    public String newProduct(Model model) {
        Product product = new Product();

        // TODO: refactor once we've got a mechanism for generating refs
        product.setRef("new");
        model.addAttribute("product", product);
        return "newproduct";
    }

    /**
     * Create a new product
     * @param product details of the product to be created
     * @return the list view
     */
    @RequestMapping(value = "/list/product/new", method = RequestMethod.POST)
    public String productCreate(@Valid Product product, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "newproduct";
        }

        // hand product off to service to be created
        service.createProduct(product);

        // on success, pass user back to the list view
        return "list";
    }

    /**
     * Delete a product
     * @param product the product to be deleted
     * @return the list view
     */
    @RequestMapping(value = "/list/product/delete", method = RequestMethod.POST)
    public String productDelete(@Valid Product product, BindingResult bindingResult) {
        // TODO: does the user have the right access to delete a product?
            // TODO: if yes
                boolean success = service.deleteProduct(product);
                // TODO: if unsuccess (i.e. invalid ref), error page?
            // TODO: if no, bump user to 403 page?

        return "list";
    }
}
