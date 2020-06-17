package com.mojitoming.casclient.entity;

import java.io.Serializable;
import java.util.List;

public class Privilege implements Serializable {
    private static final long serialVersionUID = -4070035329942296456L;

    private List<Module> system;
    private List<Module> page;
    private List<OrgVo> data;

    public List<Module> getSystem() {
        return system;
    }

    public void setSystem(List<Module> system) {
        this.system = system;
    }

    public List<Module> getPage() {
        return page;
    }

    public void setPage(List<Module> page) {
        this.page = page;
    }

    public List<OrgVo> getData() {
        return data;
    }

    public void setData(List<OrgVo> data) {
        this.data = data;
    }
}
