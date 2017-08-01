package com.onlineBankingApplication.security.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableAutoConfiguration(exclude = {
//        org.activiti.spring.boot.RestApiAutoConfiguration.class,
//        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
//        org.activiti.spring.boot.SecurityAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.onlineBankingApplication"})
@Order(99)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserSecurityService userSecurityService;

	private static final String SALT = "salt"; // Salt should be protected
												// carefully
//	@Bean
//	public ThymeleafViewResolver viewResolver() {
//	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//	    viewResolver.setViewClass(ThymeleafTilesView.class);
//	    viewResolver.setTemplateEngine(templateEngine());
//	    viewResolver.setCharacterEncoding("UTF-8");
//	    viewResolver.setOrder(0);//HERE!!
//	    return viewResolver;
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}

	private static final String[] PUBLIC_MATCHERS = { "/webjars/**", "/css/**", "/js/**", "/images/**", "/",
			"/about/**", "/contact/**", "/error/**/*", "/console/**", "/api/**", "/signUp", "/userFront","/applicant/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		// antMatchers("/**").
				antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

		http.csrf().disable().cors().disable().formLogin().failureUrl("/index?error").defaultSuccessUrl("/userFront")
				.loginPage("/index").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout")
				.deleteCookies("remember-me").permitAll().and().rememberMe();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		// //This is in-memory authentication
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
