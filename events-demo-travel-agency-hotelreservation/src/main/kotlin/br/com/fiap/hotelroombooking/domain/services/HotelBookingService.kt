package br.com.fiap.hotelroombooking.domain.services

import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.Event
import br.com.fiap.hotelroombooking.domain.events.EventProducer
import br.com.fiap.hotelroombooking.domain.events.getOutcome
import br.com.fiap.hotelroombooking.domain.events.getTopicFromOutcome
import br.com.fiap.hotelroombooking.domain.gateway.HotelBookingClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HotelBookingService(
    @Autowired val client: HotelBookingClient,
    @Autowired val eventProducer: EventProducer
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    fun bookRoom(customer: Customer) {
        val result = client.bookRoom()
        val outcome = getOutcome(result)
        logger.info("When trying to book a room at the hotel for customer ${customer.customerId} received outcome ${outcome.name}")
        eventProducer.send(
            Event.createEventFromOutcome(
                outcome,
                customer
            ),
            getTopicFromOutcome(outcome)
        )
    }

}