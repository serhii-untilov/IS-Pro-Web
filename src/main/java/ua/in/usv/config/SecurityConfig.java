package ua.in.usv.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${rememberMe.cookieName}")
    private String rememberMeCookieName;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private HikariDataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authorize requests
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("/person").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/login?error")
                // login configuration
                .and().formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/j_spring_security_check")
                        .failureUrl("/login?error")
                        .usernameParameter("j_login")
                        .passwordParameter("j_password")
                        .permitAll()
                // logout configuration
                .and().logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .permitAll();
    }

    private ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }



}