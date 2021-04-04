package br.com.fiap.start.application.kafka

import br.com.fiap.start.domain.constants.START_SAGA_TOPIC
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AutoCreateConfig {
    @Bean
    fun createSuccessTopic(): NewTopic =
        TopicBuilder.name(START_SAGA_TOPIC).partitions(3).replicas(1).build()

}