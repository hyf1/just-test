package com.hyf.springboot.justtest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)

public class User {
    @Basic
    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Basic
    @Column(name="username")
    private String username;

    @Basic
    @Column(name="password")
    private String password;

    @Basic
    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

    @Basic
    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;

    @Basic
    @Column(name="deleted")
    private  boolean deleted;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_in_group",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @JsonIgnoreProperties(value = {"users"})
    private Set<UserGroup> groups;

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
