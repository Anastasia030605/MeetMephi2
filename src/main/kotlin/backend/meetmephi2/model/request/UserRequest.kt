package backend.meetmephi2.model.request

import backend.meetmephi2.database.entity.Role

class UserRequest (val name : String, val surname : String,
                   val group : String?, val email : String, val password : String, val role : Role)