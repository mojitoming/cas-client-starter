package com.mojitoming.casclient.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Role implements Serializable {
    private static final long serialVersionUID = -9006688092480040230L;

    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Long priority;
    private String status;
    private LocalDateTime createDate;
    private String creator;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                   Objects.equals(roleName, role.roleName) &&
                   Objects.equals(roleDesc, role.roleDesc) &&
                   Objects.equals(priority, role.priority) &&
                   Objects.equals(status, role.status) &&
                   Objects.equals(createDate, role.createDate) &&
                   Objects.equals(creator, role.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDesc, priority, status, createDate, creator);
    }
}
