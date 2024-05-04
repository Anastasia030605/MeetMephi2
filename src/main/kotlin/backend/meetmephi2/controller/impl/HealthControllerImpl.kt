package backend.meetmephi2.controller.impl

import backend.meetmephi2.controller.HealthController
import backend.meetmephi2.model.message.HealthMessage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthControllerImpl : HealthController {
    @GetMapping("/public/health")
    override fun health(): HealthMessage = HealthMessage()
    @GetMapping("/health")
    override fun securedHealth() : HealthMessage = HealthMessage()
}