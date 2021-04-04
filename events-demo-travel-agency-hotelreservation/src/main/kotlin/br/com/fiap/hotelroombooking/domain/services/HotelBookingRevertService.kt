package br.com.fiap.hotelroombooking.domain.services

import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_REVERT_SUCCESS_TOPIC
import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.EventProducer
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookingRevertedEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HotelBookingRevertService(@Autowired val eventProducer: EventProducer) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun revert(customer: Customer) {
        logger.info("Hotel Room Booking Reverted for customer $customer")
        eventProducer.send(
            HotelRoomBookingRevertedEvent.create(customer),
            HOTEL_BOOKING_REVERT_SUCCESS_TOPIC
        )
    }
}