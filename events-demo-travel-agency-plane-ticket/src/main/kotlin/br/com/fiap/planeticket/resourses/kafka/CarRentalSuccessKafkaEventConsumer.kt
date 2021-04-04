package br.com.fiap.planeticket.resourses.kafka

import br.com.fiap.planeticket.domain.constants.CAR_RENT_SUCCESS_TOPIC
import br.com.fiap.planeticket.domain.entities.Customer
import br.com.fiap.planeticket.domain.events.inbound.CarRentedSuccessfullyEvent
import br.com.fiap.planeticket.domain.services.PlaneTicketService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CarRentalSuccessKafkaEventConsumer(
    @Autowired val service: PlaneTicketService,
    @Autowired val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [CAR_RENT_SUCCESS_TOPIC])
    fun onMessage(consumerRecord: ConsumerRecord<String, String>) {
        val event = objectMapper.readValue(
            consumerRecord.value(),
            CarRentedSuccessfullyEvent::class.java
        )
        val customer = event.customer


        logger.info(
            "event consumed ${
                String(
                    consumerRecord.headers().first().value()
                )
            } from topic  ${consumerRecord.topic()} for customer ${customer.customerId}"
        )
        service.buyPlaneTicket(customer)
    }
}
