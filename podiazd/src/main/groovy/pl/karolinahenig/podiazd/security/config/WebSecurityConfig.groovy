package pl.karolinahenig.podiazd.security.config

import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pl.karolinahenig.podiazd.appuser.AppUserService

@Configuration
@AllArgsConstructor
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @SuppressWarnings('GrFinalVariableAccess')
    private final AppUserService appUserService
    @Autowired
    @SuppressWarnings('GrFinalVariableAccess')
    private final BCryptPasswordEncoder bCryptPasswordEncoder

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider())
    }

    @Bean
   DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider()
        provider.setPasswordEncoder(bCryptPasswordEncoder)
        provider.setUserDetailsService(appUserService)
        return provider
    }
    @Bean
    JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

}
