package nc.opt.colisnc.config;

import nc.opt.colisnc.app.authentification.service.impl.JwtServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/colis/**").permitAll().and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), new JwtServiceImpl()))
                .httpBasic()
                .and().sessionManagement().disable();
    }
}
