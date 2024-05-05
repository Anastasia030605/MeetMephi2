package backend.meetmephi2.service.impl

import backend.meetmephi2.database.dao.UserDao
import backend.meetmephi2.database.entity.User
import backend.meetmephi2.model.mapper.UserMapper
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import backend.meetmephi2.service.TokenService
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
    private val passwordEncoder : PasswordEncoder,
    private val tokenService: TokenService
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
    override fun update(accessToken: String, id: Long, request: UserRequest): UserResponse {
        val Token = accessToken.substring(7)
        val user = userDao.findEntityById(id) ?: throw Exception("Not found")
        if(tokenService.extractEmail(Token) != user.email){
            throw Exception("Not your user")
        }
        val updated = mapper.update(user, request)
        return mapper.asResponse(updated)
    }

    @Transactional
    @Modifying
    override fun delete(accessToken: String, id: Long) {
        val Token = accessToken.substring(7)
        val user = userDao.findEntityById(id) ?: throw Exception("Not found")
        if(tokenService.extractEmail(Token) != user.email){
            throw Exception("Not your user")
        }
        userDao.delete(user)
    }
}