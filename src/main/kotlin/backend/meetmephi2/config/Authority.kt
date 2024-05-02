package backend.meetmephi2.config

import org.springframework.security.core.GrantedAuthority

const val USER_ROLE = "ROLE_USER"

enum class Authority(val authority: GrantedAuthority) {
    USER(GrantedAuthority { USER_ROLE })
}