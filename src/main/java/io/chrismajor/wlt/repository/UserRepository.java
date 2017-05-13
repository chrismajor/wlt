package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Christo on 13/05/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
