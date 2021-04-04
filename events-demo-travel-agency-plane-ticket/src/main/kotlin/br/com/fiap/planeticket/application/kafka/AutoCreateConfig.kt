package br.com.fiap.planeticket.application.kafka

import br.com.fiap.planeticket.domain.constants.*
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AutoCreateConfig {
    @Bean
    fun createSuccessTopic(): NewTopic = TopicBuilder.name(
        PLANE_TICKET_SUCCESS_TOPIC
    ).partitions(3).replicas(1).build()
    @Bean
    fun createFailureTopic(): NewTopic = TopicBuilder.name(
        PLANE_TICKET_FAILURE_TOPIC
    ).partitions(3).replicas(1).build()
}