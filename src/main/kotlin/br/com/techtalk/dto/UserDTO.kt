package br.com.techtalk.dto

import br.com.techtalk.avro.User

data class UserDTO(val name: String){

    fun toUser(): User {
        return User.newBuilder()
            .setName(this.name)
            .build()
    }

}
