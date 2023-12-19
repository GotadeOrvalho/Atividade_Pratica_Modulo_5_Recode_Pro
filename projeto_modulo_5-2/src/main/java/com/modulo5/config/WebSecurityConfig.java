package com.modulo5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.modulo5.servicos.UserDetailsServiceImpl;
import com.modulo5.enums.Perfil;

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
	            .antMatchers("/clientes/**").hasAuthority(Perfil.ADMIN.toString())
	            .antMatchers("/destinos/**").hasAuthority(Perfil.ADMIN.toString())
	            .antMatchers("/funcionarios/**").hasAuthority(Perfil.ADMIN.toString())
	            .antMatchers("/mensagens/**").hasAuthority(Perfil.ADMIN.toString())
	            .antMatchers("/passagem/**").hasAuthority(Perfil.ADMIN.toString())
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
