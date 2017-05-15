package io.chrismajor.wlt.service;

import io.chrismajor.wlt.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

/**
 * Unit tests for SecurityService
 */
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest
//@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class SecurityServiceTests {

    @Autowired
    private SecurityService securityService;

    @MockBean
    private UserDetailsService userDetailsService;

    @WithMockUser(username = "user@test.com", password = "supersecurepassword", roles = {"USER"}, authorities = {"ROLE_USER"})
    public void autoLogin() {
        String username = "user@test.com";
        String password = "supersecurepassword";

        Set<GrantedAuthority> auths = new HashSet<>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(username, password, auths);

        given(userDetailsService.loadUserByUsername(username)).willReturn(user);

        securityService.autologin(username, password);
    }
}
