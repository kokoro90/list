package dev.chidesign.list.frontend

import dev.chidesign.list.core.*
import dev.chidesign.list.facades.UserFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
class UserController(private val repository: UserRepository) {

    @Autowired
    lateinit var userFacade: UserFacade

    @GetMapping("/users")
    fun users(): List<UserInfo> {
        return userFacade.getUsers(null)
    }

    @GetMapping("usersLast/{lastName}")
    fun usersLast(@PathVariable lastName: String): List<UserInfo> {
        return userFacade.getUsers(lastName)
    }

    @PostMapping("/register")
    fun register(@RequestBody user: User, response: HttpServletResponse) {
        try {
            response.status = userFacade.createUser(user)
        } catch (e: Exception) {
            response.status = 400
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginInfo: LoginInfo, response: HttpServletResponse) {
        var token = userFacade.doLogin(loginInfo)

        if(null == token) {
            response.status = 401
        } else {
            response.status = 200
            response.addHeader("List-Authorization", token)
        }
    }

    @GetMapping("validateToken")
    fun validateToken(@RequestHeader("List-Authorization") token: String, response: HttpServletResponse) {
        response.status = if(userFacade.validateToken(token)) {
            200
        } else {
            401
        }
    }
}
