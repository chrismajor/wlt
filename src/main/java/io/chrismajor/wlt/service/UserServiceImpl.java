package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.Role;
import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.repository.RoleRepository;
import io.chrismajor.wlt.repository.UserRepository;
import io.chrismajor.wlt.ui.model.UserDetails;
import io.chrismajor.wlt.util.DataMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Service level operations for managing user CRUDing
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Persist a given user's details to the database
     * @param userDetails the user's details
     */
    @Override
    public void save(UserDetails userDetails) {
        User user = DataMappingUtil.mapNewUser(userDetails);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.getUserRole();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
    }

// TODO: remove if unused
//    /**
//     * Find a user's details by their username
//     * @param username the username
//     * @return
//     */
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
}
