package space.borisgk.itis.sem4.hw4.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import space.borisgk.itis.sem4.hw4.services.CalcService;
import space.borisgk.itis.sem4.hw4.services.SimpleCalcService;

import javax.servlet.ServletContext;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ComponentScan({ "space.borisgk.itis.sem4.hw4.controllers", "space.borisgk.itis.sem4.hw4.services" })
@EnableWebMvc
public class Config implements WebMvcConfigurer {

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new RecursiveInternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    resolver.setRedirectContextRelative(false);
    return resolver;
  }

  @Bean
  public CalcService calcService() {
    return new SimpleCalcService();
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("tests");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/asserts/**")
            .addResourceLocations("classpath:/asserts/")
            .setCachePeriod(31556926);
  }
}
