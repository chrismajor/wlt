package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.exception.*;
import io.chrismajor.wlt.service.ProductService;
import io.chrismajor.wlt.ui.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for product list operations
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

        List<Product> products;

        // get all of the active products
        try {
            products = service.getProductList();
        }
        catch (ServiceException e) {
            log.error("Service error occurred when trying to get a product's details", e);
            throw new ItBrokeException();
        }

        // display the product list
        model.addAttribute("products", products);
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

        Product product;

        try {
            // get product by ref, add to the model
            product = service.getProduct(ref);
        }
        catch (ProductNotFoundException e) {
            log.warn("Product not found when fetching via ref " + ref, e);
            throw new NotFoundException();
        }
        catch (ServiceException e) {
            log.error("Service error occurred when trying to get a product's details", e);
            throw new ItBrokeException();
        }

        // view the product details
        model.addAttribute("product", product);
        return "product";
    }

    /**
     * Update the details of a product
     * @param product the details of the updated product
     * @return the 'list' view
     */
    @RequestMapping(value = "/list/product", method = RequestMethod.POST)
    public String productUpdate(@Valid Product product, BindingResult bindingResult) {

        // if there are validation errors, return to the same form
        if(bindingResult.hasErrors()) {
            return "newproduct";
        }

        try {
            // hand product off to service to be updated
            service.updateProduct(product);
        }
        catch (ProductNotFoundException e) {
            log.error("Product not found when trying to update " + product, e);
            throw new ItBrokeException();
        }
        catch (ServiceException e) {
            log.error("Service error occurred when trying to get a product's details", e);
            throw new ItBrokeException();
        }

        // if the update has been successful, return to the product list
        return "redirect:/list?updated";
    }

    /**
     * Display the page for creating new products
     * @param model the model
     * @return the 'new product' view
     */
    @RequestMapping(value = "/list/product/new", method = RequestMethod.GET)
    public String newProduct(Model model) {
        Product product = new Product();
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

        // if there are validation errors, return to the same form
        if(bindingResult.hasErrors()) {
            return "newproduct";
        }

        // hand product off to service to be created
        try {
            service.createProduct(product);
        }
        catch (ServiceException e) {
            log.error("Service error occurred when trying to get a product's details", e);
            throw new ItBrokeException();
        }

        // on success, pass user back to the list view
        return "redirect:/list?created";
    }

    /**
     * Delete a product
     * @param ref for the product to be deleted
     * @return the list view
     */
    @RequestMapping(value = "/list/product/delete", method = RequestMethod.POST)
    public String productDelete(@RequestParam(value = "ref") String ref) {

        // get role names using snazzy functional logic
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> authNames = auth.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());

        // if user has access to delete a product...
        if (authNames.contains("ROLE_ADMIN")) {
            try {
                service.deleteProduct(ref);
            } catch (ProductNotFoundException e) {
                log.error("Product not found when trying to delete with ref " + ref, e);
                throw new ItBrokeException();
            } catch (ServiceException e) {
                log.error("Service error occurred when trying to get a product's details", e);
                throw new ItBrokeException();
            }
        }
        // else, error page
        else {
            throw new ForbiddenException();
        }

        return "redirect:/list?deleted";
    }
}
