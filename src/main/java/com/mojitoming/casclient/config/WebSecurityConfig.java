package com.mojitoming.casclient.config;

import com.alibaba.fastjson.JSON;
import com.mojitoming.casclient.entity.*;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    // 更换 CAS 中的 session 中的 key
    public final static String SESSION_KEY = AbstractCasFilter.CONST_CAS_ASSERTION;

    @Value("${session.timeout:1800}")
    private int timeout;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(securityInterceptor());

        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");

        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();

            // 判断是否已有该用户登录的 session
            if (session.getAttribute(SESSION_KEY) == null) {
                response.sendRedirect("/login");
            }

            session.setMaxInactiveInterval(timeout);

            // 获取 CAS 传递回来的对象
            Assertion assertion = (Assertion) session.getAttribute(SESSION_KEY);

            String userJsonStr = (String) assertion.getPrincipal().getAttributes().get("user");
            String roleJsonStr = (String) assertion.getPrincipal().getAttributes().get("role");
            String privilegeMapJsonStr = (String) assertion.getPrincipal().getAttributes().get("privilege");

            User user = JSON.parseObject(userJsonStr, User.class);
            Role role = JSON.parseObject(roleJsonStr, Role.class);
            Privilege privilege = JSON.parseObject(privilegeMapJsonStr, Privilege.class);

            session.setAttribute("user", user);
            session.setAttribute("role", role);
            PrivilegeBean.setPrivilege(privilege); // 设置一个静态变量，方便调用

            // 处理此系统权限
            List<Module> systemList = privilege.getSystem();
            List<Module> theSystemList = systemList.stream().filter(e -> e.getModuleAction().equals(contextPath)).collect(Collectors.toList());
            long systemId;
            if (theSystemList.size() > 0) {
                systemId = theSystemList.get(0).getModuleId();
                // 根据 systemId 获取 page、function
                List<Module> pageList = privilege.getPage();
                List<Module> functionList = privilege.getFunction();

                List<Module> thePageList = pageList.stream().filter(e -> e.getParentId().equals(systemId)).collect(Collectors.toList());
                List<Module> theFuncList = functionList.stream().filter(e -> e.getParentId().equals(systemId)).collect(Collectors.toList());

                Privilege thePrivilege = new Privilege();
                thePrivilege.setPage(thePageList);
                thePrivilege.setFunction(theFuncList);

                PrivilegeBean.setThePrivilege(thePrivilege);
            }

            return true;
        }
    }

}
