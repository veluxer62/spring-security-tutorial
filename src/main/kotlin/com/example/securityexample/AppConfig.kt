package com.example.securityexample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router {

    @Bean
    fun routes(homeHandler: HomeHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/", homeHandler::home)
        }
    }

}

@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = User.withUsername("user")
                .password(encodePassword("password"))
                .roles("USER")
                .build()

        return MapReactiveUserDetailsService(user)
    }

    private fun encodePassword(raw: String)=
            PasswordEncoderFactories.createDelegatingPasswordEncoder()
                .encode(raw)
}