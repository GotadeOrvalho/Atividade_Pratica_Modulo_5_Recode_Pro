package com.modulo5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.projetos.servicos.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	        .antMatchers("/adminlte/**").permitAll()
	            .antMatchers("/img/**").permitAll()
	            .antMatchers("/js/**").permitAll()
	            .antMatchers("/plugins/**").permitAll()
	            .anyRequest().authenticated();

	        http.formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/clientes")
	            .permitAll();
	        
	        http.logout()
            .logoutRequestMatcher(
                new AntPathRequestMatcher("/logout", "GET")
            )
            .logoutSuccessUrl("/login");
	       
	    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
}