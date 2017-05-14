package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.ui.model.UserDetails;

/**
 * Interface for the user service
 */
public interface UserService {

    /**
     * Save the user's details
     * @param userDetails the user's details
     */
    void save(UserDetails userDetails);

// TODO: remove if unused
//   /**
//    * Find a user's details using their username
//    * @param username the username
//    * @return the user's details
//    */
//   User findByUsername(String username);
}
