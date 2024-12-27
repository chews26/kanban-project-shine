package com.example.prello.user.security.config;

import com.example.prello.user.enums.Auth;
import com.example.prello.user.security.filter.AuthenticationFilter;
import com.example.prello.user.security.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private static final String[] ADMIN_REQUIRED_PATHS = {"/api/workspaces", "/api/workspaces/*/members/*"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 관리자 권한이 필요한 경로에 대한 인터셉터
        registry.addInterceptor(new AuthorizationInterceptor(Auth.ADMIN))
                .addPathPatterns(ADMIN_REQUIRED_PATHS)
                .order(Ordered.HIGHEST_PRECEDENCE + 1);
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

    @Bean
    public AuthorizationInterceptor adminAuthorizationInterceptor() {
        return new AuthorizationInterceptor(Auth.ADMIN);
    }

}
