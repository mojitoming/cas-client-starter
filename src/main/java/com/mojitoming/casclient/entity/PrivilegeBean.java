package com.mojitoming.casclient.entity;

import java.io.Serializable;

public class PrivilegeBean implements Serializable {
    private static final long serialVersionUID = 2312772731265077859L;

    private static Privilege privilege;

    public static Privilege getPrivilege() {
        return privilege;
    }

    public static void setPrivilege(Privilege privilege) {
        PrivilegeBean.privilege = privilege;
    }
}
