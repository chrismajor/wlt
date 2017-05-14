package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.Application;
import io.chrismajor.wlt.service.SecurityService;
import io.chrismajor.wlt.service.UserService;
import io.chrismajor.wlt.ui.model.UserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Unit tests for the ListController
 *
 * Operations to be tested:
 *   GET  /login
 *   GET  /register
 *   POST /register
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class SecurityControllerTests {
    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserService userService;

    @MockBean
    private SecurityService securityService;



    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     *   GET  /login
     */
    @Test
    public void loginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     *   GET  /register
     */
    @Test
    public void registerPage() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    /**
     *   POST  /register
     */
    @Test
    public void submitRegister() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("user@google.com");
        userDetails.setPassword("supersecurepassword");
        userDetails.setPasswordConfirm("supersecurepassword");
        userDetails.setForename("Ralph");
        userDetails.setSurname("Smith");
        userDetails.setDob(new Date(84,5,13));
        userDetails.setAddressLine1("1 The Street");
        userDetails.setAddressLine2("");
        userDetails.setTown("Hull");
        userDetails.setCounty("Hullshire");
        userDetails.setCountry("UK");
        userDetails.setPostcode("HH6 6UU");

        doNothing().when(userService).save(userDetails);
        doNothing().when(securityService).autologin("supersecurepassword","supersecurepassword");

        this.mockMvc.perform(
                    post("/register")
                    .param("username",       "user@google.com")
                    .param("password",       "supersecurepassword")
                    .param("passwordConfirm","supersecurepassword")
                    .param("forename",       "Ralph")
                    .param("surname",        "Smith")
                    .param("dob",            "13/06/1984")
                    .param("addressLine1",   "1 The Street")
                    .param("addressLine2",   "")
                    .param("town",           "Hull")
                    .param("county",         "Hullshire")
                    .param("country",        "UK")
                    .param("postcode",       "HH6 6UU")
                ).andExpect(status().is3xxRedirection())
                .andExpect(model().errorCount(0))
                .andExpect(model().hasNoErrors());
    }


    /**
     *   POST  /register
     */
    @Test
    public void submitRegister_emptyForm() throws Exception {
        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeHasErrors("userdetails"));
    }


    /**
     *   POST  /register
     */
    @Test
    public void submitRegister_invalidEmail() throws Exception {
        this.mockMvc.perform(
                post("/register")
                        .param("username",       "notanemailaddress")
                        .param("password",       "supersecurepassword")
                        .param("passwordConfirm","supersecurepassword")
                        .param("forename",       "Ralph")
                        .param("surname",        "Smith")
                        .param("dob",            "13/06/1984")
                        .param("addressLine1",   "1 The Street")
                        .param("addressLine2",   "")
                        .param("town",           "Hull")
                        .param("county",         "Hullshire")
                        .param("country",        "UK")
                        .param("postcode",       "HH6 6UU")
        ).andExpect(model().attributeHasFieldErrors("userdetails", "username"));
    }

    /**
     *   POST  /register
     */
    @Test
    public void submitRegister_invalidDate() throws Exception {
        this.mockMvc.perform(
                post("/register")
                        .param("username",       "user@google.com")
                        .param("password",       "supersecurepassword")
                        .param("passwordConfirm","supersecurepassword")
                        .param("forename",       "Ralph")
                        .param("surname",        "Smith")
                        .param("dob",            "this is my date of birth!")
                        .param("addressLine1",   "1 The Street")
                        .param("addressLine2",   "")
                        .param("town",           "Hull")
                        .param("county",         "Hullshire")
                        .param("country",        "UK")
                        .param("postcode",       "HH6 6UU")
        ).andExpect(model().attributeHasFieldErrors("userdetails", "dob"));
    }

    @Test
    public void submitRegister_mismatchingPassword() throws Exception {
        this.mockMvc.perform(
                post("/register")
                        .param("username",       "user@google.com")
                        .param("password",       "supersecurepassword")
                        .param("passwordConfirm","adifferentpassword")
                        .param("forename",       "Ralph")
                        .param("surname",        "Smith")
                        .param("dob",            "13/06/1984")
                        .param("addressLine1",   "1 The Street")
                        .param("addressLine2",   "")
                        .param("town",           "Hull")
                        .param("county",         "Hullshire")
                        .param("country",        "UK")
                        .param("postcode",       "HH6 6UU")
        ).andExpect(model().errorCount(1));
    }
}
