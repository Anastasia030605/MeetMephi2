package backend.meetmephi2.service

import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import org.springframework.data.domain.Page

interface UserService {
    fun list(): Page<UserResponse>
    fun getById(id: Long) : UserResponse
    fun create(request: UserRequest): UserResponse
    fun update(id : Long, request: UserRequest) : UserResponse
    fun delete(id : Long)
}