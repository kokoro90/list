package dev.chidesign.list.core

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.visible = true AND u.lastName = ?1")
    fun findByLastName(lastName: String): Iterable<User>

    @Query("SELECT u FROM User u WHERE u.visible = true")
    fun findByVisible(): Iterable<User>

    @Query("SELECT u FROM User u WHERE u.token = ?1")
    fun getUserByToken(token: String): User?
}

interface ListRepository: CrudRepository<FlexibleList, String> {

}