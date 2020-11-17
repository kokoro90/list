package dev.chidesign.list

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(private val repository: UserRepository) {
    @GetMapping("/users")
    fun users(): List<User> {
        return repository.findAll().toList()
    }

    @GetMapping("usersLast/{lastName}")
    fun usersLast(@PathVariable lastName: String): Iterable<User> {
        return repository.findByLastName(lastName)
    }
}