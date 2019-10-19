package com.mojitoming.casclient.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
// @AutoConfigureBefore(WebSecurityConfig.class)
@EnableConfigurationProperties(SpringCasProperties.class)
public class CasCustomConfig {
    @Resource
    private SpringCasProperties casProperties;

    private final boolean CAS_ENABLE = true;

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
        listener.setEnabled(CAS_ENABLE);
        listener.setListener(new SingleSignOutHttpSessionListener());
        listener.setOrder(1);

        return listener;
    }

    /**
     * Annotation:
     * 该过滤器用于实现单点登出功能，单点退出配置，一定要放在其他filter之前
     *
     * @Author: Adam Ming
     * @Date: Sep 26, 2019 at 4:12:40 PM
     */
    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
        FilterRegistrationBean<SingleSignOutFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new SingleSignOutFilter());
        filterRegistration.setEnabled(CAS_ENABLE);
        if (casProperties.getSignOutFilters().size() > 0) {
            filterRegistration.setUrlPatterns(casProperties.getSignOutFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", casProperties.getCasServerUrlPrefix());
        filterRegistration.setOrder(3);

        return filterRegistration;
    }

    /**
     * Annotation:
     * 该过滤器负责用户的认证工作
     *
     * @Author: Adam Ming
     * @Date: Sep 26, 2019 at 4:20:05 PM
     */
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new AuthenticationFilter());
        filterRegistration.setEnabled(CAS_ENABLE);
        if (casProperties.getAuthFilters().size() > 0) {
            filterRegistration.setUrlPatterns(casProperties.getAuthFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        if (casProperties.getIgnoreFilters() != null) {
            filterRegistration.addInitParameter("ignorePattern", casProperties.getIgnoreFilters());
        }
        filterRegistration.addInitParameter("casServerLoginUrl", casProperties.getCasServerLoginUrl());
        filterRegistration.addInitParameter("serverName", casProperties.getServerName());
        filterRegistration.addInitParameter("useSession", casProperties.isUseSession() ? "true" : "false");
        filterRegistration.addInitParameter("redirectAfterValidation", casProperties.isRedirectAfterValidation() ? "true" : "false");
        filterRegistration.addInitParameter("encoding", "UTF-8");
        filterRegistration.setOrder(4);

        return filterRegistration;
    }

    /**
     * Annotation:
     * 该过滤器负责对 Ticket 的校验工作，使用 CAS 3.0 协议
     *
     * @Author: Adam Ming
     * @Date: Sep 26, 2019 at 4:29:53 PM
     */
    @Bean
    public FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter> cas30ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        filterRegistration.setEnabled(CAS_ENABLE);
        if (casProperties.getValidateFilters().size() > 0) {
            filterRegistration.setUrlPatterns(casProperties.getValidateFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", casProperties.getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName", casProperties.getServerName());
        filterRegistration.setOrder(5);

        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistration.setEnabled(CAS_ENABLE);
        if (casProperties.getRequestWrapperFilters().size() > 0) {
            filterRegistration.setUrlPatterns(casProperties.getRequestWrapperFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.setOrder(6);

        return filterRegistration;
    }

    /**
     * Annotation:
     * 该过滤器使用 org.jasig.cas.client.util.AssertionHolder 来获取用户的登录名。
     * 比如 AssertionHolder.getAssertion().getPrincipal().getName()
     * 这个类把 Assertion 信息放在 ThreadLocal 变量中，这样应用程序不在 web 层也能够获取到当前登录信息
     *
     * @Author: Adam Ming
     * @Date: Sep 26, 2019 at 4:55:04 PM
     */
    @Bean
    public FilterRegistrationBean<AssertionThreadLocalFilter> assertionThreadLocalFilter() {
        FilterRegistrationBean<AssertionThreadLocalFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new AssertionThreadLocalFilter());
        filterRegistration.setEnabled(CAS_ENABLE);
        if (casProperties.getAssertionFilters().size() > 0) {
            filterRegistration.setUrlPatterns(casProperties.getAssertionFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.setOrder(7);

        return filterRegistration;
    }

}
