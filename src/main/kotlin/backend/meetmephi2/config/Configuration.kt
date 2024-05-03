package backend.meetmephi2.config

import backend.meetmephi2.database.dao.UserDao
import backend.meetmephi2.service.CustomUserDetailsService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Configuration {

    @Bean
    fun userDetailsService(userDao: UserDao) : UserDetailsService =
        CustomUserDetailsService(userDao)

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userDao: UserDao) : AuthenticationProvider =
        DaoAuthenticationProvider()
            .also{
                it.setUserDetailsService(userDetailsService(userDao))
                it.setPasswordEncoder(encoder())
            }

    @Bean
    fun autheticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}