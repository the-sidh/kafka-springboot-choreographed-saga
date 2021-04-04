package br.com.fiap.carrental.domain.events

import br.com.fiap.carrental.domain.entities.cutomerSample
import br.com.fiap.carrental.domain.events.outbound.CarRentedSuccessfullyEvent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CarRentedSuccessfullyEventTest() {


    @Test
    fun `given an entity when creating an event should return an object that represents a success event`() {
        val entity = cutomerSample()
        val event = CarRentedSuccessfullyEvent.create(entity)
        Assertions.assertEquals(
            entity.customerId,
            event.customer.customerId
        )
        Assertions.assertEquals(entity.customerId, event.customer.customerId)
    }
}