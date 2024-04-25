package backend.meetmephi2.controller

import backend.meetmephi2.model.message.DeletedMessage
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import org.springframework.data.domain.Page

interface UserController {
    fun list(): Page<UserResponse>
    fun getById(id: Long) : UserResponse
    fun create(request: UserRequest)
    fun update(id : Long, request: UserRequest) : UserResponse
    fun delete(id : Long): DeletedMessage
}