package br.com.fiap.hotelroombooking.application.kafka

import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_FAILURE_TOPIC
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_REVERT_SUCCESS_TOPIC
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_SUCCESS_TOPIC
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AutoCreateConfig {
    @Bean
    fun createSuccessTopic(): NewTopic = TopicBuilder.name(
        HOTEL_BOOKING_SUCCESS_TOPIC).partitions(3).replicas(1).build()
    @Bean
    fun createFailureTopic(): NewTopic = TopicBuilder.name(
        HOTEL_BOOKING_FAILURE_TOPIC).partitions(3).replicas(1).build()
    @Bean
    fun createRevertTopic(): NewTopic = TopicBuilder.name(
        HOTEL_BOOKING_REVERT_SUCCESS_TOPIC).partitions(3).replicas(1).build()
}