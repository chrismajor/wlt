package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.Role;
import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.repository.RoleRepository;
import io.chrismajor.wlt.repository.UserRepository;
import io.chrismajor.wlt.ui.model.UserDetails;
import io.chrismajor.wlt.util.DataMappingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Persist a given user's details to the database
     * @param userDetails the user's details
     */
    @Override
    public void save(UserDetails userDetails) {
        log.debug("saving user :: " + userDetails);

        User user = DataMappingUtil.mapNewUser(userDetails);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.getUserRole();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        log.debug("user saved successfully :: " + user);
    }
}
