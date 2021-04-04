package br.com.fiap.carrental.resourses.kafka

import br.com.fiap.carrental.domain.constants.HOTEL_BOOKING_SUCCESS_TOPIC
import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.inbound.HotelRoomBookedSuccessfullyEvent
import br.com.fiap.carrental.domain.services.CarRentalService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class HotelRoomBookingSuccessKafkaEventConsumer(
    @Autowired val service: CarRentalService,
    @Autowired val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [HOTEL_BOOKING_SUCCESS_TOPIC])
    fun onMessage(consumerRecord: ConsumerRecord<String, String>) {
        val event = objectMapper.readValue(
            consumerRecord.value(),
            HotelRoomBookedSuccessfullyEvent::class.java
        )
        val customer = event.customer

        logger.info(
            "event consumed ${
                String(
                    consumerRecord.headers().first().value()
                )
            } id ${consumerRecord.key()} from topic  ${consumerRecord.topic()} for customer ${customer.customerId}"
        )

        service.rentCar(customer)
    }
}
