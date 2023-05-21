package cl.ey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cl.ey.security.JWTAuthorizationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration {

	@Autowired
	JWTAuthorizationFilter jWTAuthorizationFilter;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.csrf().disable()
        	.authorizeHttpRequests()
        	.requestMatchers("/token", "/token/", "/token/*")
        	.permitAll()
    		.anyRequest().authenticated().and().
			exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jWTAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }	
}
