package com.mojitoming.casclient.entity;

import java.util.Objects;

public class Module {
    private String moduleId;
    private String moduleName;
    private String moduleType;
    private String moduleAction;
    private String parentId;
    private Long seqNo;
    private String moduleIcon;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
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
                Objects.equals(seqNo, module.seqNo) &&
                Objects.equals(moduleIcon, module.moduleIcon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, moduleName, moduleType, moduleAction, parentId, seqNo, moduleIcon);
    }
}
