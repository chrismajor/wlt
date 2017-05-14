package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.User;

/**
 * Created by Christo on 13/05/2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
