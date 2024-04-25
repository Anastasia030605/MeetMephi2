package backend.meetmephi2.controller.impl

import backend.meetmephi2.controller.HealthController
import backend.meetmephi2.model.message.HealthMessage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthControllerImpl : HealthController {
    @GetMapping("/health")
    override fun health(): HealthMessage = HealthMessage()
}