package backend.meetmephi2.service

import backend.meetmephi2.config.JwtProperties
import backend.meetmephi2.controller.auth.AuthenticationRequest
import backend.meetmephi2.controller.auth.AuthenticationResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService (
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val userDetailsService: UserDetailsService,
)  {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse{
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )

        return AuthenticationResponse(
            accessToken = accessToken
        )
    }
}
