package ro.tuc.ds2020.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()

              .antMatchers("/web").hasAnyAuthority("ADMIN","USER")
               .antMatchers("/web/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/").permitAll()

                        .anyRequest().authenticated().
                and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().httpBasic().and().cors();


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

