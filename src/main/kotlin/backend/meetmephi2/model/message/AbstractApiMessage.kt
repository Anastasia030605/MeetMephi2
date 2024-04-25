package backend.meetmephi2.model.message

import backend.meetmephi2.model.ApiResponse
import org.springframework.http.HttpStatus

abstract class AbstractApiMessage : ApiResponse {
    override val status: HttpStatus = HttpStatus.OK
}