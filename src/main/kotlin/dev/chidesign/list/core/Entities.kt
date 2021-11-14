package dev.chidesign.list.core

import javax.persistence.*

@Entity
class User(
    @Id
    var id: String,

    var firstName: String,

    var lastName: String,

    @Column(columnDefinition = "boolean default true")
    var visible: Boolean,

    var password: String,

    var token: String?,

    var expiration: Long?
)

@Entity
class FlexibleList(
    @Id
    var id: String,

    @ManyToMany
    var users: List<User>,

    var type: String,

    var name: String,

    var description: String,

    var timestamp: Long?,

    var checksum: String?,

    var itemsChecksum: String?,

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    var bgColor: Int,

    @Column(columnDefinition = "INTEGER DEFAULT 0xffffff")
    var color: Int,

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    var active: Boolean
)