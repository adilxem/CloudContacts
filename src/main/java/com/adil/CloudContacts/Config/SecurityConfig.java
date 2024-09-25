package com.adil.CloudContacts.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.adil.CloudContacts.Services.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {


    // @Bean
    // public UserDetailsService userDetailsService() {


    //     UserDetails user = User
    //     .withUsername("admin")
    //     .password("admin")
    //     .roles("ADMIN","USER")
    //     .build();

    //     return new InMemoryUserDetailsManager(user);
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;


    @Autowired
    private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;


    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // get the object of UserDetailsService and pass in the argument below

        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // get the object of PasswordEncoder and pass in the argument below

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;        
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        // URL configurations to authorize all non /user URL patterns
        
        httpSecurity.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });


        // customize every/ anything related to form logins here...

        httpSecurity.formLogin(formLogin -> {


            formLogin.loginPage("/login");

            formLogin.loginProcessingUrl("/authenticate");

            formLogin.defaultSuccessUrl("/user/dashboard", true);

            formLogin.failureUrl("/login?error=true");

            formLogin.usernameParameter("email");

            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException 
                      
            //             {
            //                 throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //             }
                
            // });


            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
                            
                            
            //                 throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //             }           

            // });

        });


        // httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/authenticate", "/dologout"));

        // httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(logoutForm -> {

            logoutForm.logoutUrl("/logout");

            logoutForm.logoutSuccessUrl("/login?logout=true");

        });


        // OAuth Config:

        httpSecurity.oauth2Login(oauth -> {

            oauth.loginPage("/login");

            oauth.successHandler(oAuthAuthenticationSuccessHandler);
        });

        return httpSecurity.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
