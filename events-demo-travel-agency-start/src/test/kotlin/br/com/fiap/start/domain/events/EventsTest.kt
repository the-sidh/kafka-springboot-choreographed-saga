package br.com.fiap.start.domain.events

import br.com.fiap.start.domain.entities.sagaStartSample
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EventsTest {

    @Test
    fun `given that the outcome was success when creating the event should return a event that represents the success of the operation`() {
        val content = sagaStartSample()
        assertTrue(
            Event.createEvent(
                content = content
            ) is StartSagaEvent
        )
    }
}
