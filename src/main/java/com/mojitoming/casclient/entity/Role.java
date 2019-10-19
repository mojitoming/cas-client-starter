package com.mojitoming.casclient.entity;

import java.util.Objects;

public class Role {
    private String roleId;
    private String roleCode;
    private String roleDesc;
    private Long priority;
    private String status;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(roleCode, role.roleCode) &&
                Objects.equals(roleDesc, role.roleDesc) &&
                Objects.equals(priority, role.priority) &&
                Objects.equals(status, role.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleCode, roleDesc, priority, status);
    }
}
