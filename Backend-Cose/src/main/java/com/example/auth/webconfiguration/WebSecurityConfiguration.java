package com.example.auth.webconfiguration;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.auth.filters.JwtRequestFilter;
import com.example.auth.services.admin.category.CategoryService;
import com.example.auth.services.admin.category.CategoryServiceImpl;
import com.example.auth.services.jwt.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
	
private final CustomerServiceImpl userDetailsServiceImpl;
	

	private final JwtRequestFilter authFilter;
	
	
	public WebSecurityConfiguration(CustomerServiceImpl userDetailsServiceImpl, JwtRequestFilter authFilter) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.authFilter = authFilter;
	}
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/api/**").hasRole("USER")
				.requestMatchers("/**")
				.permitAll()
				.and()
				.formLogin().loginPage("/signup").loginProcessingUrl("/login")
				.defaultSuccessUrl("/api/").and().csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
						 
	}
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//	        return security
//	        		
//	        		.csrf().disable()
//	                .authorizeHttpRequests()
//	                .requestMatchers("/signup", "/login").permitAll()
//	                .and()
//	                .authorizeHttpRequests().requestMatchers("/api/**")
//	                .authenticated()
//	                .and()
//	                .sessionManagement()
//	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	                .and()
//	                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//	                .build();
//	    }
	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.build()
//				.pathMapping("/")
//				.apiInfo(new ApiInfoBuilder()
//						.title("My API")
//						.description("API documentation")
//						.version("1.0")
//						.build());
//				
//	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}


}
