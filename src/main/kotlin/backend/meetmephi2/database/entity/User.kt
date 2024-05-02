package backend.meetmephi2.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "'user'")
class User (
    @Column(name = "name", nullable = false, length = 30)
    var name : String,

    @Column(name = "surname", nullable = false, length = 30)
    var surname : String,

    @Column(name = "'group'", nullable = true, length = 10)
    var group : String?,

    @Column(name = "email", nullable = false, length = 100)
    var email : String,

    @Column(name = "password", nullable = false, length = 100)
    var password : String,

    @ManyToMany
    @JoinTable(
        name = "'friends'",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "friend_id")]
    )
    val friends : Set<User> = HashSet(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val events: Set<Event> = HashSet()

): AbstractEntity()