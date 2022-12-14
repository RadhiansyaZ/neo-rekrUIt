package com.apb15.neorekruit.security;

import com.apb15.neorekruit.security.filter.CustomAuthenticationFilter;
import com.apb15.neorekruit.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/token/login");

        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers(
                "/**"
                ).permitAll();

        http.authorizeRequests().antMatchers(GET, "/pengguna/pendaftar").hasAnyAuthority("ROLE_PENDAFTAR");
        http.authorizeRequests().antMatchers(GET, "/pengguna/rekruter").hasAnyAuthority("ROLE_REKRUTER");

        http.authorizeRequests().antMatchers(POST, "/rekrutmen").hasAnyAuthority("ROLE_REKRUTER");
        http.authorizeRequests().antMatchers("/rekrutmen/*").hasAnyAuthority("ROLE_REKRUTER");

        http.authorizeRequests().antMatchers(POST, "/rekrutmen/**/pengumuman").hasAnyAuthority("ROLE_REKRUTER");
        http.authorizeRequests().antMatchers(PUT, "/rekrutmen/**/pengumuman/*").hasAnyAuthority("ROLE_REKRUTER");
        http.authorizeRequests().antMatchers(DELETE, "/rekrutmen/**/pengumuman/*").hasAnyAuthority("ROLE_REKRUTER");

        http.authorizeRequests().antMatchers(POST, "/rekrutmen/**/pendaftaran").hasAnyAuthority("ROLE_PENDAFTAR");
        http.authorizeRequests().antMatchers(GET,"/rekrutmen/**/pendaftaran/*").hasAnyAuthority("ROLE_PENDAFTAR",
                "ROLE_REKRUTER");
        http.authorizeRequests().antMatchers(PUT, "/rekrutmen/**/pendaftaran/*").hasAnyAuthority("ROLE_REKRUTER");
        http.authorizeRequests().antMatchers(POST, "/rekrutmen/**/pendaftaran/**/ubahstatus").hasAnyAuthority(
                "ROLE_REKRUTER");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
