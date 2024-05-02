package backend.meetmephi2.controller.impl

import backend.meetmephi2.controller.UserController
import backend.meetmephi2.model.message.DeletedMessage
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import backend.meetmephi2.service.UserService
import org.springframework.data.domain.Page
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserControllerImpl(
    private val service : UserService,
    private val encoder : PasswordEncoder
) : UserController {
    @GetMapping
    override fun list(): Page<UserResponse> = service.list()

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    override fun create(@RequestBody request: UserRequest) = service.create(request)

    @PutMapping("/{id}")
    override fun update(@PathVariable id : Long, @RequestBody request: UserRequest) = service.update(id, request)

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long) : DeletedMessage {
        service.delete(id)
        return DeletedMessage()
    }

}