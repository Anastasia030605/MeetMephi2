package backend.meetmephi2.model.response

import java.time.LocalDate

class UserResponse(override val id: Long,
                   override val createdAt: LocalDate,
                   val name : String,
                   val surname : String,
                   val group : String?,
                   val email : String) : EntityResponse