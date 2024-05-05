package backend.meetmephi2.service

import backend.meetmephi2.config.Configuration
import backend.meetmephi2.config.JwtProperties
import backend.meetmephi2.controller.auth.AuthenticationRequest
import backend.meetmephi2.controller.auth.AuthenticationResponse
import backend.meetmephi2.controller.auth.RegistrationRequest
import backend.meetmephi2.database.dao.UserDao
import backend.meetmephi2.database.entity.Role
import backend.meetmephi2.database.entity.User
import jakarta.transaction.Transactional
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
    private val userDetailsService: CustomUserDetailsService,
    private val userDao : UserDao,
)  {
    fun login(authRequest: AuthenticationRequest): AuthenticationResponse{
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

    @Transactional
    fun register(request: RegistrationRequest) : AuthenticationResponse {
        val user = User(
            name = request.name,
            group = request.group,
            surname = request.surname,
            email = request.email,
            role = Role.USER,
            password = Configuration().encoder().encode(request.password)
        )
        userDao.save(user)
        val accessToken = tokenService.generate(
            userDetails = user.mapToUserDetails(),
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthenticationResponse(accessToken)
    }

    fun logout(authRequest: AuthenticationRequest): AuthenticationResponse{
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
