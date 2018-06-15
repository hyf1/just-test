package com.hyf.springboot.justtest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="user_privilege")
@EntityListeners(AuditingEntityListener.class)

public class UserPrivilege {

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



    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "privileges")
    @JsonIgnoreProperties(value = {"privileges"})
    private Set<UserGroup> groups;


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

    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }




}
