package br.com.fiap.start.domain.events

import br.com.fiap.start.domain.entities.sagaStartSample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StartSagaEventTest() {


    @Test
    fun `given an entity when creating an event should return an object that represents a success event`() {
        val entity = sagaStartSample()
        val event = StartSagaEvent.create(entity)
        Assertions.assertEquals(
            entity.customerId,
            event.customer.customerId
        )
        Assertions.assertEquals(entity.customerId, event.customer.customerId)
    }
}