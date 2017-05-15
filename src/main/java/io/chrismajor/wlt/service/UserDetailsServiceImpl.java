package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Supporting functions for auth & registration
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve a user's details using their username
     * @param username the username
     * @return user's details
     * @throws UsernameNotFoundException if the user isn't found, or isn't valid, throw an exception
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.warn("unable to find user with username " + username);
            throw new UsernameNotFoundException("unable to find user with username " + username);
        }
        else if (user.getRoles() == null || user.getRoles().size() < 1) {
            log.error("no roles found for user " + username);
            throw new UsernameNotFoundException("no roles found for user " + username);
        }

        // create a set of GrantedAuthoritys from the retrieved user's roles
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map( r -> new SimpleGrantedAuthority(r.getName()) )
                .collect(Collectors.toSet());

        // return the user details
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
