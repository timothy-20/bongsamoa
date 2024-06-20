package com.timothy.bongsamoa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
        basePackages = "com.timothy.bongsamoa",
        excludeFilters = { @ComponentScan.Filter(Controller.class) }
)
public class TKRootContext {

}
