package com.hyf.springboot.justtest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user_group")
@EntityListeners(AuditingEntityListener.class)
public class UserGroup {
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name", unique = true)
    private String name;

    @Basic
    @Column(name = "description")
    private String description;


    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "groups")
    @JsonIgnoreProperties(value = {"groups"})
    private Set<User> users;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_group_has_privilege",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    @JsonIgnoreProperties(value = {"groups"})
    private Set<UserPrivilege> privileges;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<UserPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<UserPrivilege> privileges) {
        this.privileges = privileges;
    }
}
