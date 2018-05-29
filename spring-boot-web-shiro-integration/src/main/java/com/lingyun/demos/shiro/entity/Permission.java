package com.lingyun.demos.shiro.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_permissions")
public class Permission {
    @Id
    @GenericGenerator(name ="system-uuid", strategy = "uuid")
    @GeneratedValue(generator ="system-uuid")
    private String id;
    @Column
    private String permission;
    @Column
    private String description;
    @Column
    private Boolean available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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
}
