package dev.chidesign.list

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, String> {
    fun findByLastName(lastName: String): Iterable<User>
}
