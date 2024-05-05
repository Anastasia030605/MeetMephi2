package backend.meetmephi2.controller

import backend.meetmephi2.model.message.DeletedMessage
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import org.springframework.data.domain.Page

interface UserController {
    fun list(): Page<UserResponse>
    fun getById(id: Long) : UserResponse
    fun create(request: UserRequest) : UserResponse
    fun update(accessToken: String, id : Long, request: UserRequest) : UserResponse
    fun delete(accessToken: String, id : Long): DeletedMessage
}