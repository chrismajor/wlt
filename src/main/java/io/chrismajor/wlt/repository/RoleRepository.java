package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * CRUD repository for Role persistence
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieve the entity relating to a standard user role
     * @return the role
     */
    @Query(value = "from Role where name = 'ROLE_USER'")
    Role getUserRole();
}
