//package com.onion.dealz.api.config.security;
//
//import com.onion.dealz.api.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    private CustomAuthenticationFailureHandler authenticationFailureHandler;
//
//    @Autowired
//    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Autowired
//    private CustomLogoutSuccessHandler logoutSuccessHandler;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//
//                .antMatchers(HttpMethod.GET,"/api/promotions/all").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/promotions/add").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.GET, "/api/promotions/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/promotions/*/delete").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
//                .antMatchers(HttpMethod.PUT, "/api/promotions/**/update").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.POST, "/api/promotions/**/like", "/api/promotions/**/unlike", "/api/promotions/**/removelike", "/api/promotions/**/removeunlike").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//
//                .antMatchers(HttpMethod.GET,"/api/comments/all").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/comments/allByPromotion").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/comments/allByUser").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/comments/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/comments/add").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.DELETE,"/api/comments/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
//                .antMatchers(HttpMethod.PUT,"/api/comments/**/update").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.POST, "/api/comments/**/like", "/api/comments/**/removelike").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//
//                .antMatchers(HttpMethod.GET,"/api/shops/all").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/shops/add").hasAnyRole("ROLE_ADMIN", "ROLE_MOD")
//                .antMatchers(HttpMethod.PUT,"/api/shops/**/update").hasAnyRole("ROLE_ADMIN", "ROLE_MOD")
//                .antMatchers(HttpMethod.GET,"/api/shops/**").permitAll()
//
//                .antMatchers(HttpMethod.GET,"/api/users/all").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/users/allByName").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/users/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/api/users/auth/**/delete").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.POST,"/api/users/auth/registration").permitAll()
//
//                .antMatchers(HttpMethod.PUT,"/api/users/auth/**/password").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.PUT,"/api/users/auth/update").hasAnyRole("ROLE_ADMIN", "ROLE_MOD", "ROLE_USER")
//                .antMatchers(HttpMethod.GET,"/api/users/**/comments").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/users/**/promotions").permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/api/auth/login")
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler)
//                .and()
//                .logout()
//                .logoutUrl("/api/auth/logout")
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .and()
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                ;
//
//
//
//
////                .deleteCookies("JSESSIONID")
////                .and()
////                .csrf().disable()
//        ;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
//}