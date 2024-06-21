package com.timothy.bongsamoa.config;

import com.timothy.bongsamoa.config.appServlet.TKServletContext;
import jakarta.servlet.*;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.EnumSet;

public class TKWebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        this.registerDispatcherServlet(servletContext);
        this.registerCharacterEncodingFilter(servletContext);
    }

    private void registerDispatcherServlet(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(TKServletContext.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("appServlet", dispatcherServlet);

        if (registration != null) {
            registration.setLoadOnStartup(1);
            registration.addMapping("/");
        }

        AnnotationConfigWebApplicationContext rootApplicationContext = new AnnotationConfigWebApplicationContext();
        rootApplicationContext.register(TKRootContext.class);

        ContextLoaderListener loaderListener = new ContextLoaderListener(rootApplicationContext);
        servletContext.addListener(loaderListener);

    }

    public void registerCharacterEncodingFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());

        if (characterEncodingFilter != null) {
            characterEncodingFilter.setInitParameter("encoding", "UTF-8");
            characterEncodingFilter.setInitParameter("forceEncoding", "true");
            characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        }
    }
}
