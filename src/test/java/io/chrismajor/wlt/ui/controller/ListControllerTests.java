package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.Application;
import io.chrismajor.wlt.service.ProductService;
import io.chrismajor.wlt.ui.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

/**
 * Unit tests for the ListController
 *
 * Operations to be tested:
 *   GET  /list
 *   GET  /list/product
 *   GET  /list/product/new
 *   POST /list/product
 *   POST /list/product/new
 *   POST /list/product/delete
 *
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class ListControllerTests {
    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


/* TODO:::

implement tests for product not found exceptions
implement tests for service exceptions

auth related tests?

.andExpect(view().name("globalerrors/password"));

*/


    @Test
    public void listProducts_404() throws Exception {
        this.mockMvc.perform(get("/list/nope")).andExpect(status().is4xxClientError());
    }


    /** GET  /list */
    @Test
    public void listProducts_basic() throws Exception {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setName("Hat");
        product1.setDescription("A pretty swanky top hat");
        product1.setPrice(new BigDecimal(1000));
        product1.setRef("abc123");

        Product product2 = new Product();
        product2.setName("Bulldozer");
        product2.setDescription("It's going to dig up some crazy stuff");
        product2.setPrice(new BigDecimal(50000));
        product2.setRef("def456");

        Product product3 = new Product();
        product3.setName("Swordfish");
        product3.setDescription("Look at it's crazy face");
        product3.setPrice(new BigDecimal(1000));
        product3.setRef("abc123");

        products.add(product1);
        products.add(product2);
        products.add(product3);

        given(this.productService.getProductList()).willReturn(products);

        this.mockMvc.perform(get("/list")).andExpect(status().isOk())
                .andExpect(xpath("//h1[@id='title']").exists())
                .andExpect(xpath("//h1[@id='title']").string("Product List"));
    }

    /** GET  /list/product */
    @Test
    public void listProduct() throws Exception {
        String ref = "abc123";

        Product product1 = new Product();
        product1.setName("Hat");
        product1.setDescription("A pretty swanky top hat");
        product1.setPrice(new BigDecimal(1000));
        product1.setRef(ref);

        given(this.productService.getProduct(ref)).willReturn(product1);

        this.mockMvc.perform(get("/list/product?ref=abc123")).andExpect(status().isOk())
                .andExpect(xpath("//h1[@id='title']").exists())
                .andExpect(xpath("//h1[@id='title']").string("Product Details"));
    }

    /** GET  /list/product */
    @Test
    public void listProduct_noParam() throws Exception {
        this.mockMvc.perform(get("/list/product")).andExpect(status().is4xxClientError());
    }

    /** GET  /list/product */
    public void listProduct_invalidParam() throws Exception {

    }

    /** GET  /list/product */
    public void listProduct_noResultFound() throws Exception {

    }

    /** GET  /list/product/new */
    @Test
    public void newProductDisplay() throws Exception {
        this.mockMvc.perform(get("/list/product/new"))
                .andExpect(status().isOk())
                .andExpect(xpath("//h1[@id='title']").exists())
                .andExpect(xpath("//h1[@id='title']").string("Create a new product"));
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct() throws Exception {
        Product product1 = new Product();
        product1.setName("Hat");
        product1.setDescription("A pretty swanky top hat");
        product1.setPrice(new BigDecimal(1000));
        product1.setRef("abc123");

        given(this.productService.createProduct(product1)).willReturn(true);

        this.mockMvc.perform(post("/list/product/new")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "1000")
                .param("ref","aaa"))
                .andExpect(model().errorCount(0))
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection());
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_missingDetails() throws Exception {
        this.mockMvc.perform(post("/list/product/new"))
                .andExpect(model().attributeHasErrors("product"));
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_invalidName() throws Exception {
        this.mockMvc.perform(post("/list/product/new")
                .param("name", "")
                .param("description", "fancy top hat")
                .param("price", "1000")
                .param("ref", "hhh333"))
                .andExpect(model().attributeHasFieldErrors("product", "name"));
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_invalidDescription() throws Exception {
        this.mockMvc.perform(post("/list/product/new")
                .param("name", "Hat")
                .param("description", "")
                .param("price", "1000")
                .param("ref", "hhh333"))
                .andExpect(model().attributeHasFieldErrors("product", "description"));
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_invalidPrice() throws Exception {
        this.mockMvc.perform(post("/list/product/new")
                .param("name", "Hat")
                .param("description", "swanky old top hat")
                .param("price", "bananas")
                .param("ref", "hhh333"))
                .andExpect(model().attributeHasFieldErrors("product", "price"));
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_invalidRef() throws Exception {
        this.mockMvc.perform(post("/list/product/new")
                .param("name", "Hat")
                .param("description", "swanky old top hat")
                .param("price", "bananas")
                .param("ref", ""))
                .andExpect(model().attributeHasFieldErrors("product", "ref"));
    }

    /** POST /list/product */
    @Test
    public void updateProduct() throws Exception {
        Product product1 = new Product();
        product1.setName("Hat");
        product1.setDescription("A pretty swanky top hat");
        product1.setPrice(new BigDecimal(1000));
        product1.setRef("abc123");

        doNothing().when(this.productService).updateProduct(product1);

        this.mockMvc.perform(post("/list/product")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "1000")
                .param("ref","aaa"))
                .andExpect(model().errorCount(0))
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection());
    }

    /** POST /list/product */
    @Test
    public void updateProduct_missingDetails() throws Exception {
        this.mockMvc.perform(post("/list/product"))
                .andExpect(model().attributeHasErrors("product"));
    }

    /** POST /list/product */
    @Test
    public void updateProduct_invalidPrice() throws Exception {
        this.mockMvc.perform(post("/list/product")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "NaN")
                .param("ref","aaa"))
                .andExpect(model().attributeHasFieldErrors("product", "price"));
    }

    /** POST /list/product */
    @Test
    public void updateProduct_invalidDetailsDescription() throws Exception {
        this.mockMvc.perform(post("/list/product")
                .param("name", "Hat")
                .param("description", "")
                .param("price", "100")
                .param("ref","aaa"))
                .andExpect(model().attributeHasFieldErrors("product", "description"));
    }

    /** POST /list/product */
    @Test
    public void updateProduct_invalidDetailsName() throws Exception {
        this.mockMvc.perform(post("/list/product")
                .param("name", "")
                .param("description", "fancy top hat")
                .param("price", "100")
                .param("ref","aaa"))
                .andExpect(model().attributeHasFieldErrors("product", "name"));
    }

    /** POST /list/product */
    @Test
    public void updateProduct_invalidDetailsRef() throws Exception {
        this.mockMvc.perform(post("/list/product")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "100")
                .param("ref",""))
                .andExpect(model().attributeHasFieldErrors("product", "ref"));
    }

    /** POST /list/product/delete */
    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void deleteProduct() throws Exception {
        given(this.productService.deleteProduct("aaa")).willReturn(true);

        this.mockMvc.perform(post("/list/product/delete")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "1000")
                .param("ref","aaa"))
                .andExpect(status().is3xxRedirection());
    }

    /** POST /list/product/delete */
    @Test
    @WithMockUser(roles = {"USER"})
    public void deleteProduct_insufficientPermissions() throws Exception {
        given(this.productService.deleteProduct("aaa")).willReturn(true);

        this.mockMvc.perform(post("/list/product/delete")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "1000")
                .param("ref","aaa"))
                .andExpect(status().is4xxClientError());
    }

    /** POST /list/product/delete */
    public void deleteProduct_invalidRef() throws Exception {

    }
}
