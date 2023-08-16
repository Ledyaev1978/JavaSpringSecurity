package by.mysecurityspringgit.configuration;

import by.mysecurityspringgit.services.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.jgss.GSSUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersDetailService usersDetailService;
    @Autowired
    public SecurityConfig(UsersDetailService usersDetailService) {
        this.usersDetailService = usersDetailService;
    }

    //private  final AuthProviderImpl authProvider;
    /*@Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }*/

    //configure Spring Security and authorizaition
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            /* http
            .authorizeRequests()
            .anyRequest().permitAll() //authenticated().any
            .and()
            .formLogin().loginPage("/login")
            .loginProcessingUrl("/process_login")
            .defaultSuccessUrl("/index",true)
            .failureUrl("/login?error");*/

             http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin(login ->login
                         .loginPage("/login")
                         .defaultSuccessUrl("/products/index",true)
                         .permitAll());

                 /*loginPage()
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/index",true)
                .failureUrl("/login?error");*/

                //.csrf().disable()
                //.cors().disable()


                //.antMatchers("/login ","/error").permitAll()


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailService); //authenticationProvider(authProvider);

    }

     @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();

    }

}
