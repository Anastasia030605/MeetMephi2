package backend.meetmephi2.controller.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
)
