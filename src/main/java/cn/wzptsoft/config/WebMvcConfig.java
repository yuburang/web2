package cn.wzptsoft.config;

import cn.wzptsoft.tinymallbe.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private LoginInterceptor loginInterceptor;

  /**
   * 跨域配置
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns("*")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .maxAge(3600);
  }

//  接口访问，登录验证
  @Override
  public void addInterceptors(InterceptorRegistry registry){
    InterceptorRegistration registration =
            registry.addInterceptor(loginInterceptor);
    registration.addPathPatterns("/user/**");
    registration.addPathPatterns("/address/**");
    registration.addPathPatterns("/order/**");
  }
}
