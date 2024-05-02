package backend.meetmephi2.database.dao

import backend.meetmephi2.database.entity.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserDao : CommonDao<User> {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}