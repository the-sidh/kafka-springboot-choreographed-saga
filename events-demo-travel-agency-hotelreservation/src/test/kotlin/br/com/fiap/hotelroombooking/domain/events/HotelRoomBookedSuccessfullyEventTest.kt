package br.com.fiap.hotelroombooking.domain.events

import br.com.fiap.hotelroombooking.domain.entities.customerSampler
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookedSuccessfullyEvent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HotelRoomBookedSuccessfullyEventTest() {


    @Test
    fun `given an entity when creating an event should return an object that represents a success event`() {
        val entity = customerSampler()
        val event = HotelRoomBookedSuccessfullyEvent.create(entity)
        Assertions.assertEquals(
            entity.customerId,
            event.customer.customerId
        )
        Assertions.assertEquals(entity.customerId, event.customer.customerId)
    }
}