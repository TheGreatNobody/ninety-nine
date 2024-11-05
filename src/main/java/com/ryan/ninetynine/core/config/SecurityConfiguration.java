package com.ryan.ninetynine.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {//implements WebMvcConfigurer {
/**
 * Spring Security 5.7.0-M2 deprecated the WebSecurityConfigurerAdapter
 * 6.3 support for annotation parameters
 * */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/custom-path", "/swagger-ui.html");
//    }


        // [...]
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth)
//                throws Exception {
//            auth
//                    .inMemoryAuthentication()
//                    .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
//                    .and()
//                    .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
//        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


//@Override
//protected void configure(final HttpSecurity http) throws Exception {
//    http
//            .formLogin()
//            .loginPage("/login.html")
//            .failureUrl("/login-error.html")
//            .and()
//            .logout()
//            .logoutSuccessUrl("/index.html");
//}

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
//        return http.cors(withDefaults())
//                .csrf(withDefaults())
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .usernameParameter("email")
//                        .failureUrl("/login?loginError=true"))
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login?logoutSuccess=true")
//                        .deleteCookies("JSESSIONID"))
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        .requestMatchers("/actuator/health"
                                , "/actuator/metrics"
                                , "/actuator/metrics/**"
                                , "/v3/api-docs/**"
                                , "/swagger-ui/**"
                                , "/swagger-ui.html"
                                , "/api/monitor/**"
                                , "/api/authentication/**"
                                , "/login"
                                , "/error"
                                , "/api/**"
                                , "/webjars/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                )
                .csrf(AbstractHttpConfigurer::disable);
//        http
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/login", "/error", "/api/**").permitAll()
////                        .anyRequest().authenticated()
//                                .requestMatchers("/web/**").authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")
//                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers(
//                "/swagger-ui/", "/v3/api-docs/", "/swagger-ui.html"
//        );
//    }
}


