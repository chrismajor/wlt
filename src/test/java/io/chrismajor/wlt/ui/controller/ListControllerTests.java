package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the ListController
 *
 * Operations to be tested:
 *   GET  /list
 *   GET  /list/product
 *   GET  /list/product/new
 *   POST /list/product/new
 *   POST /list/product/update
 *   POST /list/product/delete
 *
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class ListControllerTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void listProducts_404() throws Exception {
        this.mockMvc.perform(get("/list/nope")).andExpect(status().is4xxClientError());
    }


    /** GET  /list */
    @Test
    public void listProducts_basic() throws Exception {
        this.mockMvc.perform(get("/list")).andExpect(status().isOk())
                .andExpect(xpath("//h1[@id='title']").exists())
                .andExpect(xpath("//h1[@id='title']").string("Product List"));
//        this.mockMvc.perform(get("/accounts/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.name").value("Lee"));
    }

    // TODO: list products no results
    // TODO: list products with results

    /** GET  /list */
    public void listProducts_noDb() throws Exception {

    }

    /** GET  /list/product */
    public void listProduct() throws Exception {

    }

    /** GET  /list/product */
    public void listProduct_noParam() throws Exception {

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
        this.mockMvc.perform(post("/list/product/new")
                .param("name", "Hat")
                .param("description", "fancy top hat")
                .param("price", "1000"))
                .andExpect(status().isOk());
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_missingDetails() throws Exception {
        this.mockMvc.perform(post("/list/product/new"))
                .andExpect(model().attributeHasErrors("product"));
    }

    /** POST /list/product/new */
    @Test
    public void saveNewProduct_invalidDetails() throws Exception {
        this.mockMvc.perform(post("/list/product/new")
                .param("name", "")
                .param("description", "fancy top hat")
                .param("price", "1000"))
                .andExpect(model().attributeHasErrors("product"));

        // TODO: flesh out more, and XSS
    }

    /** POST /list/product/update */
    public void updateNewProduct() throws Exception {

    }

    /** POST /list/product/update */
    public void updateNewProduct_missingDetails() throws Exception {

    }

    /** POST /list/product/update */
    public void updateNewProduct_invalidDetails() throws Exception {

    }

    /** POST /list/product/delete */
    public void deleteProduct() throws Exception {

    }

    /** POST /list/product/delete */
    public void deleteProduct_invalidRef() throws Exception {

    }
}
