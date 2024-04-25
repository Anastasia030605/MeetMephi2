package backend.meetmephi2.service.impl

import backend.meetmephi2.database.dao.UserDao
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import backend.meetmephi2.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.awt.print.Pageable

@Service
class UserServiceImpl(
    private val userDao : UserDao
) : UserService {
    override fun list(): Page<UserResponse> {
        val users = userDao.findAll(PageRequest.of(0, 20, Sort.by("surname").ascending()))
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): UserResponse {
        TODO("Not yet implemented")
    }

    override fun create(request: UserRequest) {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, request: UserRequest): UserResponse {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}