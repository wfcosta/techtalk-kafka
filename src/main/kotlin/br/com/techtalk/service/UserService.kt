package br.com.techtalk.service

import br.com.techtalk.avro.User
import br.com.techtalk.dto.UserDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserService(private val kafkaTemplate: KafkaTemplate<String, User>, private val kafkaTemplateError: KafkaTemplate<String, String>) {

    @Value("\${spring.kafka.topic.name}")
    lateinit var topicName: String

    fun send(user: UserDTO) {

        this.kafkaTemplate.send(topicName, user.toUser())
        println("Send message to kafka: $user")

    }

    fun sendWithError() {

        this.kafkaTemplateError.send(topicName, "test: xpto")

    }

}