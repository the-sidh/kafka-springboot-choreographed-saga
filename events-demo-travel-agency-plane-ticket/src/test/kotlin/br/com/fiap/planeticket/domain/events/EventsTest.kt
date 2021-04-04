package br.com.fiap.planeticket.domain.events

import br.com.fiap.planeticket.domain.entities.planeTicketSample
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketFailedEvent
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketSuccessfullyEvent
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EventsTest {

    @Test
    fun `given that the outcome was success when creating the event should return a event that represents the success of the operation`() {
        val content = planeTicketSample()
        assertTrue(
            Event.createEventFromOutcome(
                outcome = Outcome.SUCCESS,
                content = content
            ) is PlaneTicketSuccessfullyEvent
        )
    }


    @Test
    fun `given that the outcome was failure when creating the event should return a event that represents the failure of the operation`() {
        val content = planeTicketSample()
        assertTrue(
            Event.createEventFromOutcome(
                outcome = Outcome.FAILURE,
                content = content
            ) is PlaneTicketFailedEvent
        )
    }

    @Test
    fun `Given true when generating an Outcome should get SUCCESS`(){
        assertEquals(Outcome.SUCCESS, getOutcome(true))
    }
    @Test
    fun `Given false when generating an Outcome should get FAILURE`(){
        assertEquals(Outcome.FAILURE, getOutcome(false))
    }
}
