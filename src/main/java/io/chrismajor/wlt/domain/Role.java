package io.chrismajor.wlt.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity bean to describe a user's role
 */
@Entity
public class Role {
    /** primary key */
    private Long id;

    /** the role name */
    private String name;

    /** users that have the given role */
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
