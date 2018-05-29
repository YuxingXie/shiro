package com.lingyun.demos.shiro.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_users")
public class User {
    @Id
    @GenericGenerator(name ="system-uuid", strategy = "uuid")
    @GeneratedValue(generator ="system-uuid")
    private String id;
    @Column
    private String username;
    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private Boolean locked;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
