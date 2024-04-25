package backend.meetmephi2.model.response

import java.time.LocalDate

interface EntityResponse {
    val id: Long
    val createdAt: LocalDate
}