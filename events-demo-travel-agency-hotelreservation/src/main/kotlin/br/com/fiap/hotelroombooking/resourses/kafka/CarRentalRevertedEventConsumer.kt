package br.com.fiap.hotelroombooking.resourses.kafka

import br.com.fiap.hotelroombooking.domain.constants.CAR_RENTAL_FAILURE_TOPIC
import br.com.fiap.hotelroombooking.domain.constants.CAR_RENTAL_REVERTED_TOPIC
import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.inbound.CarRentRevertedEvent
import br.com.fiap.hotelroombooking.domain.events.inbound.StartSagaEvent
import br.com.fiap.hotelroombooking.domain.services.HotelBookingRevertService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CarRentalRevertedEventConsumer(
    @Autowired val service: HotelBookingRevertService,
    @Autowired val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [CAR_RENTAL_REVERTED_TOPIC, CAR_RENTAL_FAILURE_TOPIC])
    fun onMessage(consumerRecord: ConsumerRecord<String, String>) {
        val event = objectMapper.readValue(
            consumerRecord.value(),
            CarRentRevertedEvent::class.java
        )
        val customer = event.customer

        logger.info(
            "event consumed ${
                String(
                    consumerRecord.headers().first().value()
                )
            }  from topic ${consumerRecord.topic()} for customer ${customer.customerId}"
        )
        service.revert(customer)
    }
}
