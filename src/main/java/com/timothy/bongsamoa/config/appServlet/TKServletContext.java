package com.timothy.bongsamoa.config.appServlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "com.timothy.bongsamoa",
        useDefaultFilters = false,
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}
)
public class TKServletContext implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
//        viewResolver.setOrder(1);
//        registry.viewResolver(viewResolver);
//    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
        viewResolver.setOrder(1);
        return viewResolver;
    }
}
