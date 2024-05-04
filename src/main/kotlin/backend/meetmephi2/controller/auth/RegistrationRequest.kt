package backend.meetmephi2.controller.auth

import backend.meetmephi2.database.entity.Role
import jakarta.persistence.Column

class RegistrationRequest(
    email : String,
    password: String,
    val name : String,
    val surname : String,
    val group : String?,
) : AuthenticationRequest(email, password)
