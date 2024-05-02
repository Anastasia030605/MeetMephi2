package backend.meetmephi2.service

import backend.meetmephi2.config.JwtProperties
import com.nimbusds.openid.connect.sdk.AuthenticationRequest
import com.nimbusds.openid.connect.sdk.AuthenticationResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
)  {

}
