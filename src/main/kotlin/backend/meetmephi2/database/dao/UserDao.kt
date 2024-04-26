package backend.meetmephi2.database.dao

import backend.meetmephi2.database.entity.User
import org.springframework.stereotype.Repository

@Repository
interface UserDao : CommonDao<User>