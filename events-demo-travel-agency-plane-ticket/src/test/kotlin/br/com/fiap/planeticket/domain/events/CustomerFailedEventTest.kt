package br.com.fiap.planeticket.domain.events

import br.com.fiap.planeticket.domain.entities.planeTicketSample
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketFailedEvent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CustomerFailedEventTest {


    @Test
    fun `given an entity when creating an event should return an object that represents a failure event`() {
        val entity = planeTicketSample()
        val event = PlaneTicketFailedEvent.create(entity)
        Assertions.assertEquals(
            entity.customerId,
            event.customer.customerId
        )
        Assertions.assertEquals(entity.customerId, event.customer.customerId)
    }
}
