package io.chrismajor.wlt.service;

import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.ui.model.UserDetails;

/**
 * Created by Christo on 13/05/2017.
 */
public interface UserService {

    void save(UserDetails userDetails);

    User findByUsername(String username);
}
