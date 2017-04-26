/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.security;

import br.com.ararati.enums.Role;
import br.com.ararati.faces.UserAuthenticationHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import br.com.ararati.session.commons.UsuarioLoginServiceRemote;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Leandroc
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        try {
            InitialContext ctx = new InitialContext();
            UsuarioLoginServiceRemote userFacade = (UsuarioLoginServiceRemote) ctx.lookup("br.com.ararati.session.commons.UsuarioLoginServiceRemote");
            auth.userDetailsService(userFacade).passwordEncoder(new BCryptPasswordEncoder());
        } catch (NamingException ex) {
            Logger.getLogger(SecurityConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                //                .exceptionHandling().and().authorizeRequests().and()
                .exceptionHandling().accessDeniedPage("/access.xhtml").and().authorizeRequests()
                .antMatchers("/javax.faces.resource/**", "/login").permitAll()
                .and().authorizeRequests()
                .antMatchers("/dashboard.xhtml").access("hasRole('ROLE_" + Role.COMUM + "')")
                .antMatchers("/interface/cadastros/**", "/interface/parametros/**", "/interface/nfe/**", "/**", "/dashboard.xhtml", "/login").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and().formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login.xhtml").permitAll()
                .defaultSuccessUrl("/dashboard.xhtml")
                .loginProcessingUrl("/login.xhtml").permitAll()
                .failureUrl("/error.xhtml").permitAll()
                .failureHandler(new UserAuthenticationHandler()).permitAll()
                //todas as paginas serão https    
                .and()
                .httpBasic()
                .and()
                .requiresChannel()
                .anyRequest()
                .requiresSecure()
                .and()
                .portMapper() //mapeia o https para a porta 8181 (que é a porta ssl default do glassfish)
                .http(8080).mapsTo(8181);

//        
//           http
//    .authorizeRequests().antMatchers("/login.xhtml", "/login", "/dashboard", "/error.xhtml").anonymous().and()
//    .formLogin()
//    .loginPage("/login.xhtml") 
//    .defaultSuccessUrl("/successful-login")
//    .loginProcessingUrl("/login")
//    .failureUrl("/error.xhtml")
//        .permitAll();
//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.xhtml")
//                
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
