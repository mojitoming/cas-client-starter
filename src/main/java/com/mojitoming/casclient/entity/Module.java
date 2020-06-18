package com.mojitoming.casclient.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Module implements Serializable {
    private static final long serialVersionUID = -3491047505773696559L;

    private Long moduleId;
    private String moduleName;
    private String moduleType;
    private String moduleAction;
    private Long parentId;
    private Long odn;
    private String moduleIcon;
    private String status;
    private LocalDateTime createDate;
    private String creator;

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleAction() {
        return moduleAction;
    }

    public void setModuleAction(String moduleAction) {
        this.moduleAction = moduleAction;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOdn() {
        return odn;
    }

    public void setOdn(Long odn) {
        this.odn = odn;
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
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
        Module module = (Module) o;
        return Objects.equals(moduleId, module.moduleId) &&
                   Objects.equals(moduleName, module.moduleName) &&
                   Objects.equals(moduleType, module.moduleType) &&
                   Objects.equals(moduleAction, module.moduleAction) &&
                   Objects.equals(parentId, module.parentId) &&
                   Objects.equals(odn, module.odn) &&
                   Objects.equals(moduleIcon, module.moduleIcon) &&
                   Objects.equals(status, module.status) &&
                   Objects.equals(createDate, module.createDate) &&
                   Objects.equals(creator, module.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, moduleName, moduleType, moduleAction, parentId, odn, moduleIcon, status, createDate, creator);
    }
}
