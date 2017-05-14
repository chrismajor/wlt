package io.chrismajor.wlt.repository;

import io.chrismajor.wlt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Christo on 13/05/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "from Role where name = 'ROLE_USER'")
    Role getUserRole();
}
