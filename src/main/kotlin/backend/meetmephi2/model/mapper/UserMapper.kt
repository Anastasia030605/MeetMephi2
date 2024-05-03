package backend.meetmephi2.model.mapper

import backend.meetmephi2.database.entity.User
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun asEntity(request: UserRequest) = User(
        name = request.name,
        email = request.email,
        group = request.group,
        surname = request.surname,
        password = request.password,
        role = request.role
    )

    fun asResponse(user : User) = UserResponse(
        name = user.name,
        email = user.email,
        group = user.group,
        surname = user.surname,
        id = user.id,
        createdAt = user.createdAt
    )

    fun asListResponse(users : Collection<User>) = users.map { asResponse(it) }
}