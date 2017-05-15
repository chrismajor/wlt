package io.chrismajor.wlt.service;

import io.chrismajor.wlt.Application;
import io.chrismajor.wlt.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.BDDMockito.given;

/**
 * Unit tests for UserDetailsService
 */
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest
//@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class UserDetailsServiceTests {
    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    public void loadUserByUsername() {
        String username = "user@test.com";

        io.chrismajor.wlt.domain.User user = new io.chrismajor.wlt.domain.User();
        user.setUsername(username);
        user.setPassword("password");

        given(userRepository.findByUsername(username)).willReturn(user);

        userDetailsService.loadUserByUsername(username);
    }

    public void loadUserByUsername_noUserFound() {
        String username = "user@test.com";

        given(userRepository.findByUsername(username)).willReturn(null);

        try {
            userDetailsService.loadUserByUsername(username);

            // fail if we get this far
            Assert.fail();
        }
        catch (UsernameNotFoundException e) {
            Assert.assertNotNull(e);
        }
    }
}
