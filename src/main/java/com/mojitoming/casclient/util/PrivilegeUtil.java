package com.mojitoming.casclient.util;

import com.mojitoming.casclient.entity.Module;
import com.mojitoming.casclient.entity.Privilege;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Annotation:
 * 权限工具
 *
 * @Author: Adam Ming
 * @Date: Jul 9, 2020 at 9:42:21 AM
 */
public class PrivilegeUtil implements Serializable {

    private static final long serialVersionUID = -7044205748961144819L;

    private static Privilege privilege;
    private static Privilege thePrivilege; // 当前系统的权限

    public static Privilege getPrivilege() {
        return privilege;
    }

    public static void setPrivilege(Privilege privilege) {
        PrivilegeUtil.privilege = privilege;
    }

    /*
     * Annotation:
     * 获取当前系统的权限
     *
     * @Author: Adam Ming
     * @Date: Jul 9, 2020 at 11:21:33 AM
     */
    public static Privilege getThePrivilege() {
        String contextPath = YmlUtils.getValueByKeys("server", "servlet", "context-path");
        // 处理此系统权限
        List<Module> systemList = privilege.getSystem();
        List<Module> theSystemList = systemList.stream().filter(e -> e.getModuleAction().equals(contextPath)).collect(Collectors.toList());

        if (theSystemList.size() > 0) {
            long systemId = theSystemList.get(0).getModuleId();

            return getThePrivilege(systemId);
        }

        return new Privilege();
    }

    public static Privilege getThePrivilege(long parentId) {
        // 根据 parentId 获取 page、function
        List<Module> pageList = privilege.getPage();
        List<Module> functionList = privilege.getFunction();

        List<Module> thePageList = pageList.stream().filter(e -> e.getParentId().equals(parentId)).collect(Collectors.toList());
        List<Module> theFuncList = functionList.stream().filter(e -> e.getParentId().equals(parentId)).collect(Collectors.toList());

        Privilege thePrivilege = new Privilege();
        thePrivilege.setPage(thePageList);
        thePrivilege.setFunction(theFuncList);

        PrivilegeUtil.setThePrivilege(thePrivilege);


        return thePrivilege;
    }

    public static void setThePrivilege(Privilege thePrivilege) {
        PrivilegeUtil.thePrivilege = thePrivilege;
    }
}
