package com.mau.msgboardV3_SBoot.spring.config;

import com.mau.test.HelloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Servlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<Servlet> testServlet() {
        return new ServletRegistrationBean<>(new HelloServlet(), "/helloServlet");
    }
}
