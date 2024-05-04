package backend.meetmephi2.controller

import backend.meetmephi2.model.message.HealthMessage

interface HealthController {
    fun health() : HealthMessage

    fun securedHealth() : HealthMessage
}