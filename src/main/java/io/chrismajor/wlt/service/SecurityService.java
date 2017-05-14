package io.chrismajor.wlt.service;

/**
 * Created by Christo on 13/05/2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
