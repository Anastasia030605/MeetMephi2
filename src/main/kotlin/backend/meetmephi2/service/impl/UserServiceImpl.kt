package backend.meetmephi2.service.impl

import backend.meetmephi2.database.dao.UserDao
import backend.meetmephi2.database.entity.User
import backend.meetmephi2.model.mapper.UserMapper
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import backend.meetmephi2.service.UserService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Modifying
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.awt.print.Pageable

@Service
class UserServiceImpl(
    private val userDao : UserDao,
    private val mapper : UserMapper,
    private val passwordEncoder : PasswordEncoder
) : UserService {
    override fun list(): Page<UserResponse> {
        val users = userDao.findAll(PageRequest.of(0, 20, Sort.by("surname").ascending()))
        return mapper.asPageResponse(users)
    }

    override fun getById(id: Long): UserResponse {
        val user = userDao.findEntityById(id) ?: throw Exception("Not found")
        return mapper.asResponse(user)
    }

    @Transactional
    override fun create(request: UserRequest): UserResponse {
        val user = mapper.asEntity(request)
        user.password = passwordEncoder.encode(user.password)
        user.apply { userDao.save(this) }
        return mapper.asResponse(user)
    }

    @Transactional
    @Modifying
    override fun update(id: Long, request: UserRequest): UserResponse {
        val user = userDao.findEntityById(id) ?: throw Exception("Not found")
        val updated = mapper.update(user, request)
        return mapper.asResponse(updated)
    }

    @Transactional
    @Modifying
    override fun delete(id: Long) {
        val user = userDao.findEntityById(id) ?: throw Exception("Not found")
        userDao.delete(user)
        TODO("Not yet implemented")
    }
}