package dev.chidesign.list.facades

import dev.chidesign.list.*
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class UserFacade(private val repository: UserRepository) {

    fun getUsers(lastName: String?): List<UserInfo> {
        val result = ArrayList<UserInfo>()

        val users = if(null == lastName) repository.findByVisible() else repository.findByLastName(lastName)

        for (user in users) {
            result.add(user.toUserInfo())
        }

        return result
    }

    fun createUser(user: User): Int {
        return if (repository.existsById(user.id)) {
            409
        } else {
            user.password = user.password.sha1()
            repository.save(user)
            201
        }
    }

    fun doLogin(loginInfo: LoginInfo): String? {
        var token: String? = null

        try {
            val user: User = repository.findById(loginInfo.id).get()

            if (loginInfo.password.sha1() == user.password) {
                val expiration: Long = Calendar.getInstance().time.time / 1000 + 1800
                val randomComponent = Random.nextLong().toString()
                token = (loginInfo.id + loginInfo.password + expiration.toString() + randomComponent).sha512()
                user.token = token
                user.expiration = expiration
                repository.save(user)
            }
        } catch (e: Exception) {}

        return token
    }

    fun validateToken(token: String): Boolean {
        var isValid = false

        val user = repository.getUserByToken(token)
        val now: Long = Calendar.getInstance().time.time / 1000

        if(user?.expiration != null && now < user.expiration!!) {
            user.expiration = now + 1800
            repository.save(user)
            isValid = true
        }

        return isValid
    }
}