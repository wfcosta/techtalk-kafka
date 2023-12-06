package br.com.techtalk.controller

import br.com.techtalk.dto.UserDTO
import br.com.techtalk.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @PostMapping("/v1/users")
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        this.userService.send(userDTO)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/v1/users/error")
    fun createUserError(): ResponseEntity<UserDTO> {
        this.userService.sendWithError()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}