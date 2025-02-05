package com.mau.msgboardV3_SBoot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/view/");  // The location of your JSP files
//        resolver.setPrefix("/WEB-INF/jsp/");  // The one for the test disable as there can only
                                                //be one configured.
        resolver.setSuffix(".jsp");           // The file extension
        return resolver;
    }
}
