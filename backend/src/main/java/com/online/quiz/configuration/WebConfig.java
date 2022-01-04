//package com.online.quiz.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**");
////            .allowCredentials(true)
////            .allowedOrigins("http://localhost:8080")
////            .allowedMethods("GET,POST,DELETE,PUT,OPTIONS")
////            .allowedHeaders("*");
//  }
//
////  @Override
////  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
////          throws IOException, ServletException {
////    HttpServletResponse response = (HttpServletResponse) servletResponse;
////    HttpServletRequest request = (HttpServletRequest) servletRequest;
////
////    response.setHeader("Access-Control-Allow-Origin", "http://192.168.100.6:8080");
////    response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
////    response.setHeader("Access-Control-Allow-Headers", "*");
////    response.setHeader("Access-Control-Allow-Credentials", "true");
////    response.setHeader("Access-Control-Max-Age", "180");
////    filterChain.doFilter(servletRequest, servletResponse);
////  }
//
//}
//
