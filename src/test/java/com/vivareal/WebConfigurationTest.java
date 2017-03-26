package com.vivareal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fpillo on 3/26/2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.vivareal.http"})
public class WebConfigurationTest extends WebMvcConfigurerAdapter {
}
