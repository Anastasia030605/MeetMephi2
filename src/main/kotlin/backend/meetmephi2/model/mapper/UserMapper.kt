package backend.meetmephi2.model.mapper

import backend.meetmephi2.database.entity.User
import backend.meetmephi2.model.request.UserRequest
import org.springframework.data.domain.Page
import backend.meetmephi2.model.response.UserResponse
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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

    fun asPageResponse(users : Page<User>) : PageImpl<UserResponse>{
        val list = users.content.map { asResponse(it) }
        val pageable = PageRequest.of(users.number, users.size)
        return PageImpl<UserResponse>(list, pageable, users.totalElements)
    }
}