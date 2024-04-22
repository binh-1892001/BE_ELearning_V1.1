package elearning.security;

import elearning.security.jwt.CustomAccessDeniedHandler;
import elearning.security.jwt.JwtEntryPoint;
import elearning.security.jwt.JwtTokenFilter;
import elearning.security.user_principal.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SercurityConfig {
	@Autowired
	private JwtEntryPoint jwtEntryPoint;
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	@Autowired
	private UserDetailService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				  .cors(auth -> auth.configurationSource(request -> {
					  CorsConfiguration config = new CorsConfiguration();
					  config.setAllowedOrigins(List.of("https://test.mosoftvn.com/", "http://test.mosoftvn.com/", "http://localhost:5173/", "http://10.101.14.69:5173/"));
					  config.setAllowedMethods(List.of("*"));
					  config.setAllowCredentials(true);
					  config.setAllowedHeaders(List.of("*"));
					  config.setExposedHeaders(List.of("Authorization"));
					  return config;
				  }))
				  .csrf(AbstractHttpConfigurer::disable)
				  .authenticationProvider(authenticationProvider())
				  .authorizeHttpRequests((auth) ->
							 auth.requestMatchers(
												  "/api/v1/auth/**",
												  "/public/**",
												  "/api/v1/user/register",
												  "/api/v1/user-clipboard",
												  "/api/img/**",
												  "/api/v1/course/**",
												  "/api/v1/file/upload-file",
												  "/api/v1/teacher/**",
												  "/api/v1/chapter/**",
												  "/api/v1/lesson/**",
												  "/api/v1/blogs/{id}","/api/v1/blogs/"
										).permitAll()
										.anyRequest().authenticated())
				  .exceptionHandling((auth) ->
							 auth.authenticationEntryPoint(jwtEntryPoint)
										.accessDeniedHandler(customAccessDeniedHandler))
				  .sessionManagement((auth) -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				  .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
				  .build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
