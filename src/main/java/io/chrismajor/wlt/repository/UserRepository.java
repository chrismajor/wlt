package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CRUD repository for User persistence
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
