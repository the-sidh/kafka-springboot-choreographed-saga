package br.com.fiap.hotelroombooking.resourses.kafka

import br.com.fiap.hotelroombooking.domain.constants.START_SAGA_TOPIC
import br.com.fiap.hotelroombooking.domain.events.inbound.StartSagaEvent
import br.com.fiap.hotelroombooking.domain.services.HotelBookingService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class StartSagaKafkaEventConsumer(
    @Autowired val service: HotelBookingService,
    @Autowired val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [START_SAGA_TOPIC])
    fun onMessage(consumerRecord: ConsumerRecord<String, String>) {
        val startSagaEvent = objectMapper.readValue(
            consumerRecord.value(),
            StartSagaEvent::class.java
        )
        val customer = startSagaEvent.customer
        logger.info(
            "event consumed ${
                String(
                    consumerRecord.headers().first().value()
                )
            } id ${consumerRecord.key()} from topic  ${consumerRecord.topic()} for customer ${customer.customerId}"
        )
        service.bookRoom(customer)
    }
}
