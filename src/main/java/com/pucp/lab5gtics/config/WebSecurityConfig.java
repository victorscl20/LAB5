package com.pucp.lab5gtics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/inicio")
                .loginProcessingUrl("/validarLogin")
                .defaultSuccessUrl("/redirectCategoria", true);

        http.authorizeRequests()
                .antMatchers("/empleado","/buscar/**").hasAnyAuthority("Manager", "Employee")
                .antMatchers("/empleado","/guardar/**").hasAnyAuthority("Manager")
                .antMatchers("/empleado","/editar/**").hasAnyAuthority("Manager")
                .antMatchers("/personaje","/nuevo/**").hasAnyAuthority("Manager")
                .anyRequest().permitAll();

        http.logout()
                .logoutSuccessUrl("/inicio");
    }

    @Autowired
    DataSource dataSource;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT Codigo, contrasenia, Activo FROM usuario WHERE Codigo = ?")
                .authoritiesByUsernameQuery("SELECT u.Codigo, c.nombre FROM usuario u " +
                        "    INNER JOIN categoria c ON (u.idCategoria = c.idCategoria) WHERE u.Codigo = ? and u.activo = 1");

    }

}
