package backend.meetmephi2.service

import backend.meetmephi2.database.dao.UserDao
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias  ApplicationUser = backend.meetmephi2.database.entity.User

@Service
class CustomUserDetailsService(
    private val userDao: UserDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userDao.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.name)
            .password(this.password)
            .roles(this.role.name)
            .build()
}