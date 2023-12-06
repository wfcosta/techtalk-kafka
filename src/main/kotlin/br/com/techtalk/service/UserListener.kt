package br.com.techtalk.service

import br.com.techtalk.avro.User
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.DltHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Service


@Service
class UserListener {


    @RetryableTopic(
        attempts = "\${spring.kafka.consumer.retry-attempts}",
        backoff = Backoff(delay = 1000, multiplier = 2.0),
        topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
        include = [RuntimeException::class]
    )
    @KafkaListener(topics = ["\${spring.kafka.topic.name}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consume(@Payload record: ConsumerRecord<String, User>, ack: Acknowledgment) {

        throw RuntimeException("test");

        //se não tiver uma implementação de retry e dlt igual a do exemplo do spring e precisar implementar manualmente, sempre devolva o ack assim que começar a processar a mensagem.
        ack.acknowledge()

        println("Consumed message: $record")
        println("Processar registro {${record.value()}}");
        println("key: " + record.key());
        println("Headers: " + record.headers());
        println("topic: " + record.topic());
        println("Partion: " + record.partition());
        println("User Record Name: " + record.value());
        val user: User = record.value();
        println("User Object name: " + user.name);

    }

    @DltHandler
    fun dlt(@Payload record: ConsumerRecord<String, User>, @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {

        println("Consumed message: $record")
        println("Processar registro {${record.value()}}");
        println("key: " + record.key());
        println("Headers: " + record.headers());
        println("topic: " + record.topic());
        println("Partion: " + record.partition());
        println("User Record Name: " + record.value());
        val user: User = record.value();
        println("User Object name: " + user.name);

    }

}