package com.mojitoming.casclient.entity;

public class PrivilegeBean {
    private static Privilege privilege;

    public static Privilege getPrivilege() {
        return privilege;
    }

    public static void setPrivilege(Privilege privilege) {
        PrivilegeBean.privilege = privilege;
    }
}
