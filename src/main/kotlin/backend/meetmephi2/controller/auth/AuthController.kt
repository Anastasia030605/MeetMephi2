package backend.meetmephi2.controller.auth


import backend.meetmephi2.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/auth")
class AuthController (
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse =
        authenticationService.login(authRequest)


    @PostMapping("/registration")
    fun registration(@RequestBody authRequest: RegistrationRequest) : AuthenticationResponse =
        authenticationService.register(authRequest)

    @PostMapping("/logout")
    fun logout(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse =
        authenticationService.logout(authRequest)
}