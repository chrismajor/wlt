package io.chrismajor.wlt.service;

/**
 * Interface for the security service
 */
public interface SecurityService {

    /**
     * Find the username for the logged in user
     * @return the username
     */
    String findLoggedInUsername();

    /**
     * Log the user in with the provided username and password
     * @param username the username
     * @param password the password
     */
    void autologin(String username, String password);
}
