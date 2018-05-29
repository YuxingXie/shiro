package com.lingyun.demos.shiro.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_roles")
public class Role {
    @Id
    @GenericGenerator(name ="system-uuid", strategy = "uuid")
    @GeneratedValue(generator ="system-uuid")
    private String id;
    @Column
    private String role;
    @Column
    private String description;
    @Column
    private Boolean available;
    @OneToMany(targetEntity = Permission.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id", unique = true) )
    private List<Permission> permissions;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
