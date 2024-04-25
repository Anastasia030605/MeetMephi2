package backend.meetmephi2.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "'event'")
class Event (
    @Column(name = "day", nullable = false, length = 15)
    var day : String,

    @Column(name = "time", nullable = false)
    var time: Pair<Int, Int>,

    @Column(name = "name", nullable = false, length = 100)
    var name : String,

    @Column(name = "teacher", nullable = true, length = 100)
    var teacher : String,

    @Column(name = "room", nullable = false, length = 50)
    var room : String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User

) : AbstractEntity()