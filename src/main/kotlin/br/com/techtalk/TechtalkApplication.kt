package br.com.techtalk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class TechtalkApplication

fun main(args: Array<String>) {
	runApplication<TechtalkApplication>(*args)
}
