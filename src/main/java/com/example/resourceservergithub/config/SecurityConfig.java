package com.example.resourceservergithub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .oauth2Login();

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public ClientRegistrationRepository registrationRepository() {
        return new InMemoryClientRegistrationRepository(githubClient());
    }

    private ClientRegistration githubClient() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("<YOUR_CLIENT_ID_GOES_HERE>")
                .clientSecret("<YOUR_CLIENT_SECRET_GOES_HERE>")
                .build();
    }
}
