package ie.portfolio.upload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers("/upload", "/success")
                                                .authenticated()
                                                .anyRequest().permitAll())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/upload", true)
                                                .permitAll())
                                .logout((logout) -> logout.permitAll())
                                .csrf().disable()
                                .headers().frameOptions().disable();

                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                String encodedPassword = passwordEncoder.encode("password");
                UserDetails user = User.builder()
                                .username("user")
                                .password(encodedPassword)
                                .roles("USER")
                                .build();

                return new InMemoryUserDetailsManager(user);
        }
}
