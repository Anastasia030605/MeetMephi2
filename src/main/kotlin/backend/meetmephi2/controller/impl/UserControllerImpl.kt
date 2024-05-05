package backend.meetmephi2.controller.impl

import backend.meetmephi2.controller.UserController
import backend.meetmephi2.model.message.DeletedMessage
import backend.meetmephi2.model.request.UserRequest
import backend.meetmephi2.model.response.UserResponse
import backend.meetmephi2.service.UserService
import org.springframework.data.domain.Page
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

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
    override fun update(@RequestHeader("Authorization") accessToken: String, @PathVariable id : Long, @RequestBody request: UserRequest) = service.update(accessToken, id, request)

    @DeleteMapping("/{id}")
    override fun delete(@RequestHeader("Authorization") accessToken: String, @PathVariable id: Long) : DeletedMessage {
        service.delete(accessToken, id)
        return DeletedMessage()
    }

}