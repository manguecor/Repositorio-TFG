package com.tfg.apuesta.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic().disable();
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/resources/","/webjars/","/h2-console/**").permitAll()
				.antMatchers("/users/**").permitAll()
				.antMatchers("/clients/**").permitAll()
				.antMatchers("/hola").permitAll()
				.antMatchers("/prueba").permitAll()
				.antMatchers("/competitions").permitAll()
				.antMatchers("/competition/{competitionId}").permitAll()
				.antMatchers("/scorers/{competitionId}").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin");				
				/*.anyRequest().denyAll()
				.and()
				 	.formLogin().disable()
				 	.loginPage("/login")
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
		  		http.csrf().ignoringAntMatchers("/h2-console/**");
		  		http.headers().frameOptions().sameOrigin();*/
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}
