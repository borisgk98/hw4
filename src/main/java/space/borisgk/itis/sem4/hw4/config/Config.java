package space.borisgk.itis.sem4.hw4.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import space.borisgk.itis.sem4.hw4.services.CalcService;
import space.borisgk.itis.sem4.hw4.services.SimpleCalcService;

import javax.servlet.ServletContext;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Configuration
@ComponentScan({ "space.borisgk.itis.sem4.hw4.controllers", "space.borisgk.itis.sem4.hw4.services", "space.borisgk.itis.sem4.hw4.repositories" })
@EnableWebMvc
@Import(YAMLMessageSourceConfig.class)
public class Config implements WebMvcConfigurer {

  @Bean
  public ViewResolver viewResolver() {
//    InternalResourceViewResolver resolver = new RecursiveInternalResourceViewResolver();
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
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
            .addResourceLocations("/WEB-INF/asserts/")
            .setCachePeriod(31556926);
  }

  @Bean
  public MessageSource messageSource() {
//    ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
//    res.setBasenames("classpath:i18n/messages", "classpath:i18n/validation");
//    res.setCacheSeconds(0);
//    res.setDefaultEncoding("UTF-8");
//    res.setUseCodeAsDefaultMessage(false);
//    return res;
//      return new DatabaseMessageSource();
    return new YAMLMessageSource();
  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setCookieName("lang");
    localeResolver.setDefaultLocale(new Locale("ru", "RU"));
    return localeResolver;
  }
}
