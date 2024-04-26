package backend.meetmephi2.model.response

import java.time.LocalDate
import java.time.LocalDateTime

interface EntityResponse {
    val id: Long
    val createdAt: LocalDateTime
}