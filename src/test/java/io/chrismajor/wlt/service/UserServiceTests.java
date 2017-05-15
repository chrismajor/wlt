package io.chrismajor.wlt.service;

import io.chrismajor.wlt.Application;
import io.chrismajor.wlt.domain.Role;
import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.repository.RoleRepository;
import io.chrismajor.wlt.repository.UserRepository;
import io.chrismajor.wlt.ui.model.UserDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

/**
 * Unit tests for UserService
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void save() {
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

        User testUser = new User();
        testUser.setUsername("user@google.com");
        testUser.setPassword("supersecurepassword");

        Role role = new Role();
        role.setName("ROLE_USER");

        given(roleRepository.getUserRole()).willReturn(role);
        given(userRepository.save(testUser)).willReturn(testUser);
        userService.save(userDetails);

    }
}
