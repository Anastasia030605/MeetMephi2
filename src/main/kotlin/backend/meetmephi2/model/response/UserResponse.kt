package backend.meetmephi2.model.response

import java.time.LocalDateTime

class UserResponse(override val id: Long,
                   override val createdAt: LocalDateTime,
                   val name: String,
                   val surname: String,
                   val group: String?,
                   val email: String) : EntityResponse