package br.com.fiap.carrental.application.kafka

import br.com.fiap.carrental.domain.constants.CAR_RENTAL_FAILURE_TOPIC
import br.com.fiap.carrental.domain.constants.CAR_RENTAL_REVERTED_TOPIC
import br.com.fiap.carrental.domain.constants.CAR_RENTAL_SUCCESS_TOPIC
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AutoCreateConfig {
    @Bean
    fun createSuccessTopic(): NewTopic = TopicBuilder.name(
        CAR_RENTAL_SUCCESS_TOPIC).partitions(3).replicas(1).build()
    @Bean
    fun createFailureTopic(): NewTopic = TopicBuilder.name(
        CAR_RENTAL_FAILURE_TOPIC).partitions(3).replicas(1).build()
    @Bean
    fun createRevertTopic(): NewTopic = TopicBuilder.name(
        CAR_RENTAL_REVERTED_TOPIC).partitions(3).replicas(1).build()
}