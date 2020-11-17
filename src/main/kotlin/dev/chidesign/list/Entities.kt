package dev.chidesign.list

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

    var token: String,

    var expiration: Int)
