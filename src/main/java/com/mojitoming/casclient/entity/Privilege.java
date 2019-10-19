package com.mojitoming.casclient.entity;

import java.util.List;

public class Privilege {
    private List<Module> system;
    private List<Module> page;

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
}
