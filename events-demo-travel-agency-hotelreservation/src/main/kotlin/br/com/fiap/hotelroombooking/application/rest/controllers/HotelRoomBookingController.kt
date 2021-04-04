package br.com.fiap.hotelroombooking.application.rest.controllers

import br.com.fiap.hotelroombooking.application.rest.dto.HotelRoomBookingDTO
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_SUCCESS_TOPIC
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookedSuccessfullyEvent
import br.com.fiap.hotelroombooking.domain.events.EventProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HotelRoomBookingController(@Autowired @Qualifier("kafkaEventProducer") val eventProducer: EventProducer) {
    @PostMapping("/hotel/book")
    fun execute(@RequestBody hotelRoomBookingDTO: HotelRoomBookingDTO): ResponseEntity<HotelRoomBookedSuccessfullyEvent> {
        val event = HotelRoomBookedSuccessfullyEvent.create(customer = HotelRoomBookingDTO.toDomainObjet(hotelRoomBookingDTO))
        eventProducer.send(event, HOTEL_BOOKING_SUCCESS_TOPIC)
        return ResponseEntity.status(HttpStatus.CREATED).body(event)
    }
}
