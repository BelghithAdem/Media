package com.example.Media.Security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
<<<<<<< HEAD
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
=======
>>>>>>> master
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
<<<<<<< HEAD
import org.springframework.web.filter.CorsFilter;
=======
>>>>>>> master

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

<<<<<<< HEAD


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigurationSecuriteApplication{
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtFilter jwtFilter;
  private final UserDetailsService userDetailsService;
  public ConfigurationSecuriteApplication(BCryptPasswordEncoder bCryptPasswordEncoder, JwtFilter jwtFilter, UserDetailsService userDetailsService) {
=======
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigurationSecuriteApplication {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtFilter jwtFilter;
  private final UserDetailsService userDetailsService;

  public ConfigurationSecuriteApplication(BCryptPasswordEncoder bCryptPasswordEncoder, JwtFilter jwtFilter,
      UserDetailsService userDetailsService) {
>>>>>>> master
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtFilter = jwtFilter;
    this.userDetailsService = userDetailsService;
  }

<<<<<<< HEAD

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .components(new Components()
        .addSecuritySchemes("bearer-jwt",
          new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")))
      .info(new Info()
        .title("Spring Security Demo")
        .version("1.0")
        .description("A sample project on Spring Security using Spring Boot 3.0.2."))
      .addSecurityItem(
        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
=======
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("bearer-jwt",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization")))
        .info(new Info()
            .title("Spring Security Demo")
            .version("1.0")
            .description("A sample project on Spring Security using Spring Boot 3.0.2."))
        .addSecurityItem(
            new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
>>>>>>> master
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
<<<<<<< HEAD
          .addMapping("/**")
          .allowedMethods(CorsConfiguration.ALL)
          .allowedHeaders(CorsConfiguration.ALL)
          .allowedOriginPatterns(CorsConfiguration.ALL);
=======
            .addMapping("/**")
            .allowedMethods(CorsConfiguration.ALL)
            .allowedHeaders(CorsConfiguration.ALL)
            .allowedOriginPatterns(CorsConfiguration.ALL);
>>>>>>> master
      }
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
<<<<<<< HEAD
    return
      httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
          authorize ->
            authorize
              .requestMatchers(GET,
                "/v2/api-docs",
                "/swagger-ui.html",
                "/configuration/**",
                "/swagger*/**",
                "/swagger.json",
                "/webjars/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/"
              ).permitAll()
              .requestMatchers(POST,"/inscription").permitAll()
              .requestMatchers(POST,"/activation").permitAll()
              .requestMatchers(POST,"/connexion").permitAll()
              .requestMatchers(POST,"/user/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/conversation").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/posts/image/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/stomp-endpoint/**").permitAll()
              .anyRequest().authenticated()
        )
        .sessionManagement(httpSecuritySessionManagementConfigurer ->
          httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
=======
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers(GET,
                    "/v2/api-docs",
                    "/swagger-ui.html",
                    "/configuration/**",
                    "/swagger*/**",
                    "/swagger.json",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/")
                .permitAll()
                .requestMatchers(POST, "/inscription").permitAll()
                .requestMatchers(POST, "/inscription1").permitAll()
                .requestMatchers(POST, "/verify").permitAll()
                .requestMatchers(POST, "/activation").permitAll()
                .requestMatchers(POST, "/connexion").permitAll()
                .requestMatchers(POST, "/user/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/conversation").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts/image/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/stomp-endpoint/**").permitAll()
                .anyRequest().authenticated())
        .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
>>>>>>> master

        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

<<<<<<< HEAD

  @Bean
  public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
=======
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
>>>>>>> master
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
<<<<<<< HEAD
  public AuthenticationProvider authenticationProvider () {
=======
  public AuthenticationProvider authenticationProvider() {
>>>>>>> master
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
    return daoAuthenticationProvider;
  }
<<<<<<< HEAD
=======

>>>>>>> master
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

<<<<<<< HEAD

=======
>>>>>>> master
}
