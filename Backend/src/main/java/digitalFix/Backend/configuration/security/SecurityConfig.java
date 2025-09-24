package digitalFix.Backend.configuration.security;

import digitalFix.Backend.Util.JwtUtils;
import digitalFix.Backend.service.implementation.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtUtils jwtUtils) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {

                    // Autorizacion
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // Departamento
                    http.requestMatchers(HttpMethod.GET, "/departamento").hasAnyRole("USER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/departamento").hasAnyRole("USER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/departamento").hasAnyRole("USER", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/departamento/**").hasAnyRole("USER", "ADMIN");

                    // Trabajadores
                    http.requestMatchers(HttpMethod.GET, "/trabajador").hasAnyRole("USER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/trabajador").hasAnyRole("USER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/trabajador").hasAnyRole("USER", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/trabajador/**").hasAnyRole("USER", "ADMIN");

                    // Authorization


                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailService userDetailService){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
