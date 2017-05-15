package io.chrismajor.wlt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Provide security functions: provide current logged in user, and auto login user after registering an account
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Find the username for the logged in user - for RememberMe functionality
     * @return the username
     */
    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        else {
            return null;
        }
    }

    @Override
    public void autologin(String username, String password) {
        // grab the user's details & authenticate their password auth token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);

        // if it's good, add it to the security context
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
//            log.debug(String.format("Auto login %s successfully!", username));
        }
    }
}
