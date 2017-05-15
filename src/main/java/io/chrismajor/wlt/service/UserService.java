package io.chrismajor.wlt.service;

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
}
