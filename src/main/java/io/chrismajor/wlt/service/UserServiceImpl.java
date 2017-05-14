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
import java.util.List;
import java.util.Set;

/**
 * Created by Christo on 13/05/2017.
 */
// TODO: unify security & user service?
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
