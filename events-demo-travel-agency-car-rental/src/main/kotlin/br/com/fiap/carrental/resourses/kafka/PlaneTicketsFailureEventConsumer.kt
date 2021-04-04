package br.com.fiap.carrental.resourses.kafka

import br.com.fiap.carrental.domain.constants.PLANE_TICKETS_FAILURE_TOPIC
import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.inbound.HotelRoomBookedSuccessfullyEvent
import br.com.fiap.carrental.domain.events.inbound.PlaneTicketFailedEvent
import br.com.fiap.carrental.domain.services.CarRentRevertService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PlaneTicketsFailureEventConsumer(
    @Autowired val service: CarRentRevertService,
    @Autowired val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [PLANE_TICKETS_FAILURE_TOPIC])
    fun onMessage(consumerRecord: ConsumerRecord<String, String>) {
        val event = objectMapper.readValue(
            consumerRecord.value(),
            PlaneTicketFailedEvent::class.java
        )
        val customer = event.customer

        logger.info(
            "event consumed ${
                String(
                    consumerRecord.headers().first().value()
                )
            } id ${consumerRecord.key()} from topic  ${consumerRecord.topic()}for customer ${customer.customerId}"
        )
        service.revert(customer)
    }
}
